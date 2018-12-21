package com.myexperiments.MySpringExperiments.controller;

import com.myexperiments.MySpringExperiments.domain.Order;
import com.myexperiments.MySpringExperiments.domain.UserAccount;
import com.myexperiments.MySpringExperiments.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@SessionAttributes("order")
@RequestMapping("/orders")
public class OrderController {

    private OrderProperties orderProperties;
    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, @Valid  OrderProperties orderProperties) {
        this.orderRepository = orderRepository;
        this.orderProperties = orderProperties;
    }

    /**
     * By default with SpringBoot the template name is derived from the logical view name by prefixing it with
     * /templates/ and postfixing it with .html
     * */
    @PostMapping
    public String processOrder(@Valid Order order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal UserAccount userAccount) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        // This binds the User to the Order.
        order.setUser(userAccount);

        log.info("Order submitted: " + order);
        orderRepository.save(order);

        /*
        * SessionStatus parameter calls its setComplete() method to reset the session. If not called, an old Order
        * will remain in place in the session and the user will start his order with the old Order.
        */
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping("/current")
    public String orderForm(Model model,
                            @AuthenticationPrincipal UserAccount userAccount) {
        Order order = new Order();

        // This pre-populates the User Details once the User Lands on the Order Page.
        order.setDeliveryName(userAccount.getFullname());
        order.setDeliveryStreet(userAccount.getStreet());
        order.setDeliveryCity(userAccount.getCity());
        order.setDeliveryState(userAccount.getState());
        order.setDeliveryZip(userAccount.getZip());
        model.addAttribute("order", order);
        return "orderForm";
    }

    @GetMapping()
    public String getOrdersForUser(Model model, @AuthenticationPrincipal UserAccount userAccount) {
        Pageable pageable = PageRequest.of(0, orderProperties.getPageSize());

        List<Order> orderByPlacedAtDesc = orderRepository.findByUserOrderByPlacedAtDesc(userAccount, pageable);
        model.addAttribute("orders", orderByPlacedAtDesc);

        return "orderList"; // This page does not exist
    }

}
