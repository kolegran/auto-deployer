package kolegran.github.com.autodeployer;

import lombok.Data;

@Data
public class GitWebhookCommand {
    private GitBucket repository;
}
