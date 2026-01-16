package assemble.api.apiPayload.handler;

import assemble.api.apiPayload.status.ErrorReason;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final ErrorReason errorReason;

}
