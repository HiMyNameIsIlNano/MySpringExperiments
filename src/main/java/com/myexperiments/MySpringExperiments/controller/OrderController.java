package com.myexperiments.MySpringExperiments.controller;

import com.myexperiments.MySpringExperiments.domain.Order;
import com.myexperiments.MySpringExperiments.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@SessionAttributes("order")
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * By default with SpringBoot the template name is derived from the logical view name by prefixing it with
     * /templates/ and postfixing it with .html
     * */
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

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
    public String orderForm(Model model) {
        //model.addAttribute("order", new Order());
        return "orderForm";
    }

}
