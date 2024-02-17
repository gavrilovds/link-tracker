package edu.java.client.github;

import edu.java.client.dto.github.GetRepoResponse;

public interface GithubClient {

    GetRepoResponse getRepository(String path);
}
