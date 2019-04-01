package kolegran.github.com.autodeployer;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GitService {
    private final DeployService deployService;

    public void cloneRepository(Map<String, Object> payload) {
        Map<String, Object> repository = (Map<String, Object>) payload.get("repository");

        try {
            Git.cloneRepository()
                    .setURI((String) repository.get("url"))
                    .setDirectory(new File("src/main/resources/repository"))
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }

        deployService.deployProject();

        try {
            FileUtils.deleteDirectory(new File("src/main/resources/repository"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
