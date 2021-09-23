package hello.core.web;

import hello.core.common.JunyHarangLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoSevice {
    
    private final JunyHarangLogger jhLogger;
    
    public void logic(String id) {
        jhLogger.log("서비스에서 넘어온 동무 id는 다음과 같습네다! \n" + id);
    } // logic()끝
} // Closs 끝
