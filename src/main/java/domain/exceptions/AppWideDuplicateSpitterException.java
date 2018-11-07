package domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        code = HttpStatus.NOT_FOUND, reason = "(App Wide) The spitter already exists"
)
public class AppWideDuplicateSpitterException extends RuntimeException {
}
