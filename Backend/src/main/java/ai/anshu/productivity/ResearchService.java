package ai.anshu.productivity;

//@Value from Spring:
//org.springframework.beans.factory.annotation.Value is used to inject values
// from application.properties or application.yml.
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
//@Value from Lombok:
//lombok.Value is for creating immutable classes (like @Data, but final).
//It has nothing to do with Spring’s property injection.


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class ResearchService {


// injecting values from the application.properties
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

//    String url = geminiApiUrl+geminiApiKey;


// this will help in deserializing the json response in form class of our given choice. "Jackson library"
    private final ObjectMapper objectMapper;



// creating the instance of WebClient(from reactive service dependency) to make api call
    private final WebClient webClient;

    public ResearchService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper){
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }


 // Crafting/Build the prompt
    private static String buildPromt(ResearchRequest request){
        StringBuilder prompt = new StringBuilder();
        switch (request.getOperation()){
            case "summarize" :
                prompt.append("Provide a clear and concise summary of the following text in a few sentences:\n\n");
                break;
            case "suggest" :
                prompt.append("Based on the following content: suggest related topics and further reading. Format the response with clear headings and bullet points. \n\n");
                break;
            default:
                throw new IllegalArgumentException("Unknown operation for the request:" + request);
        }

        prompt.append(request.getContent());
        return prompt.toString();
    }

// to process the content -- making api calls etc
    public String processContent(ResearchRequest request){
        // Build the prompt
        String prompt = buildPromt(request);

        // query the ai model using api
            // 1. crafting the request body -> compatible to Gemini
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );
        System.out.println("Gemini API URL: " + geminiApiUrl+geminiApiKey);
            // 2. calling the api with the crafted requestBody
        String response = webClient.post()
                .uri(geminiApiUrl+geminiApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(
        status -> status.is4xxClientError() || status.is5xxServerError(),
        clientResponse -> clientResponse.bodyToMono(String.class)
                        .map(errorBody -> new RuntimeException("API Error: " + errorBody))
                )
                .bodyToMono(String.class)
                .block();

        // then parse the response
        // return the response
        return extractTextFromResponse(response);
    }


    private String extractTextFromResponse(String response){
        try{
            // converting the json into object type of GeminiResponse
            GeminiResponse geminiResponse = objectMapper.readValue(response, GeminiResponse.class);
            if(geminiResponse.getCandidates() != null && !geminiResponse.getCandidates().isEmpty()){
               GeminiResponse.Candidate firstCandidate = geminiResponse.getCandidates().get(0);
               if (firstCandidate.getContent()!=null
                       && firstCandidate.getContent().getParts() != null
                       && !firstCandidate.getContent().getParts().isEmpty()){
                   return firstCandidate.getContent().getParts().get(0).getText();
               }
            }
            return "No content found in response";
        }catch (Exception e){
            return "Error Parsing: "+e.getMessage();
        }
    }

}
