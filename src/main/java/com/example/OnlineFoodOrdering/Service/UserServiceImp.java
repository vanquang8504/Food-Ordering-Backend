package com.example.OnlineFoodOrdering.Service;

import com.example.OnlineFoodOrdering.config.JwtProvider;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFormJwtToken(jwt);
        User user = findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new Exception("User not found");
        }

        return user;
    }
}
