package edu.java.service;

import edu.java.client.dto.github.GetRepoResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface GithubService {

    @GetExchange("/repos/{owner}/{repo}")
    GetRepoResponse getRepository(@PathVariable String owner, @PathVariable String repo);
}
