package kolegran.github.com.autodeployer;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GitService {
    private final DeployService deployService;

    public void cloneRepository(GitWebhookCommand cmd) {
        try {
            FileUtils.deleteDirectory(new File("src/main/resources/repository"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Git.cloneRepository()
                    .setURI(cmd.getRepository().getUrl())
                    .setDirectory(new File("src/main/resources/repository"))
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }

        deployService.deployProject();
    }
}
