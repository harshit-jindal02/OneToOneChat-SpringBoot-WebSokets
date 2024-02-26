package com.project.ChatAppOneToOne.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUsers(User user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnectUser(User user) {
        var storeUser = repository.findById(user.getNickName()).orElse(null);
        if (storeUser != null) {
            storeUser.setStatus(Status.OFFLINE);
            repository.save(storeUser);
        }
    }

    public List<User> getUsers() {
        return repository.findAlByStatus(Status.ONLINE);
    }
}
