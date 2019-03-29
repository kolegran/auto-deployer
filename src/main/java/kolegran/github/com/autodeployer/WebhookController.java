package kolegran.github.com.autodeployer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class WebhookController {
    private final GitService gitService;

    @PostMapping("/api/github/push")
    public ResponseEntity<Void> receiveWebhook(@RequestBody @Valid Map<String, Object> payload) {
        gitService.cloneRepository(payload);
        return ResponseEntity.ok().build();
    }
}
