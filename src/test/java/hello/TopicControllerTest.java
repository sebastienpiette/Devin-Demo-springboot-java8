package hello;

import hello.controller.TopicController;
import hello.service.TopicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TopicController.class)
@ContextConfiguration(classes = {TopicController.class, TopicService.class})
class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllTopicsReturnsSeededTopics() throws Exception {
        mockMvc.perform(get("/topic"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id",
                        containsInAnyOrder("spring", "java", "javascript")));
    }

    @Test
    void getSpringTopicReturnsDetails() throws Exception {
        mockMvc.perform(get("/topic/spring"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("spring"))
                .andExpect(jsonPath("$.subjectName").value("Spring Framework"))
                .andExpect(jsonPath("$.subjectDescription").value("Spring Framework Description"));
    }
}
