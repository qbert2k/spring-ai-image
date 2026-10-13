package guru.springframework.springaiimage.services;

import guru.springframework.springaiimage.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final OpenAiImageModel imageModel;

    @Override
    public byte[] getImage(Question question) {

        var options = OpenAiImageOptions.builder()
                .withHeight(1024).withWidth(1024)
                .withModel("gpt-image-1")
                .withQuality("high")
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(question.question(), options);

        var imageResponse = imageModel.call(imagePrompt);

        return Base64.getDecoder().decode(imageResponse.getResult().getOutput().getB64Json());
    }

    @Override
    public String getDescription(MultipartFile file) {
        return "";
    }
}
