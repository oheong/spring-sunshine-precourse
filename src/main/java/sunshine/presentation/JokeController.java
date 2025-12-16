package sunshine.presentation;

import java.util.Map;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JokeController {

    private final ChatClient client;

    public JokeController(ChatClient.Builder builder) {
        this.client = builder.build();
    }

    @GetMapping("/joke")
    public ChatResponse getJoke(@RequestParam(defaultValue = "eong") String name,
                                @RequestParam(defaultValue = "pirate") String voice) {
        var user = new UserMessage("""
                Tell me about three famous pirates from the Golden Age of Piracy and what they did.
                Write at least one sentence for each pirate.
                """);

        var template = new SystemPromptTemplate("""
                You are a helpful AI assistant.
                You are an AI assistant that helps people find information.
                Your name is {name}.
                You should reply to the user's request using your name and in the style of a {voice}.
                """);

        var system = template.createMessage(Map.of("name", name, "voice", voice));
        var prompt = new Prompt(user, system);

        return client.prompt(prompt)
                .call()
                .chatResponse();
    }

    @GetMapping("/joke2")
    public String getJoke2(@RequestParam(defaultValue = "Tell me a joke about {topic}") String message) {
        return client.prompt(message)
                .call()
                .content();
    }

    @GetMapping("/joke3")
    public ChatResponse getJoke3(@RequestParam(defaultValue = "Tell me a joke about {topic}") String message,
                                 @RequestParam(defaultValue = "programming") String topic) {
        var template = new PromptTemplate(message);
        var prompt = template.render(Map.of("topic", topic));
        return client.prompt(prompt)
                .call()
                .chatResponse();
    }
}

