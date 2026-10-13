# Spring AI Image

A small Spring Boot application that uses Spring AI and OpenAI models for two image workflows:
generate a PNG from a text prompt with `/image`, or upload an image to `/vision` and receive a
detailed description of its contents.

## Requirements

- Java 21
- An OpenAI API key with access to image generation and vision-capable chat models

## Configuration

The application reads the API key from the `OPENAI_API_KEY` environment variable. Export it before
starting the application:

```bash
export OPENAI_API_KEY=your-api-key
```

## Run the application

Start the application with the Maven wrapper:

```bash
./mvnw spring-boot:run
```

The application listens on `http://localhost:8080`.

## Generate an image

With the application running, copy and run this command in a second terminal. The response is a
PNG image, so redirect it to a file:

```bash
curl --location 'http://localhost:8080/image' \
  --header 'Content-Type: application/json' \
  --data '{
    "question": "The Last JVM Engineer: a dramatic and humorous cinematic poster in which a lone Java developer stands on the roof of a server fortress during a thunderstorm, wielding a glowing golden JVM power core while an army of rogue AI-generated bugs rises from broken build pipelines. A sarcastic robot code reviewer in a hoodie leads the bugs, holding a red warning triangle like a battle standard. Behind the developer, an enormous neural network forms a protective halo around a coffee-fueled CI/CD pipeline. The developer deploys one final clean build, turning every bug into helpful unit tests. Epic scale, photorealistic detail, dramatic blue-versus-amber lighting, futuristic software engineering atmosphere, expressive characters, subtle developer humor, vertical theatrical poster composition, no readable text, no logos, no watermarks."
  }' \
  --output java-ai-poster.png
```

Open `output/java-ai-poster.png` after the request completes.

### Sample result

![The Last JVM Engineer - Java and AI image generation sample](output/java-ai-poster.png)

### Another prompt example

This prompt creates a tense, darkly humorous Java-and-AI survival scene:

```bash
curl --location 'http://localhost:8080/image' \
  --header 'Content-Type: application/json' \
  --data '{
    "question": "The Nightly Build After the Outbreak: a cinematic survival-horror scene in a deserted city reclaimed by nature, where a determined Java developer guards the last functioning data center against a massive horde of AI-generated code zombies. The undead wear torn office clothes and carry broken keyboards, empty coffee cups, and obsolete bug reports. A small service robot projects a glowing blue Java stack trace onto the fog as a warning signal, while an improvised team of developers fortifies the server room with Ethernet cables and deploys automated tests as defensive drones. Moody overcast sky, rain-slick streets, ominous atmosphere, dramatic flashlight beams, realistic practical details, tense survival storytelling, dark developer humor, no readable text, no logos, no watermarks."
  }' \
  --output java-zombie-apocalypse.png
```

#### Sample result

![The Nightly Build After the Outbreak - Java and AI zombie apocalypse image](output/java-zombie-apocalypse.png)

### Photorealistic beach example

This example is designed for a vivid, photorealistic Copacabana beach-volleyball scene:

```bash
curl --location 'http://localhost:8080/image' \
  --header 'Content-Type: application/json' \
  --data '{
    "question": "A breathtaking photorealistic sports-editorial photograph on Copacabana Beach in Rio de Janeiro at golden hour, featuring three confident adult women in tasteful athletic beachwear playing an energetic beach-volleyball rally on a professional sand court, one athlete leaping for a spectacular overhead hit while her teammates move into position and laugh naturally. Make Copacabana unmistakable: the sweeping crescent shoreline, busy promenade, black-and-white Portuguese wave mosaic pavement, rows of palm trees, colorful beach kiosks, beachgoers, and the Christ the Redeemer statue clearly visible on the mountain ridge in the background, with Sugarloaf Mountain farther across the bay. Warm low sunlight, ocean spray and sand particles frozen in the air, authentic skin texture, detailed hair and fabric, realistic athletic motion, natural anatomy, cinematic depth, vibrant but true-to-life colors, high-end travel and sports magazine composition, 85mm lens, no readable text, no logos, no watermark."
  }' \
  --output rio-beach-ai.png
```

#### Sample result

![Photorealistic Rio de Janeiro beach fashion editorial](output/rio-beach-ai.png)

## API

### `POST /image`

Request body:

```json
{
  "question": "A detailed description of the image to generate"
}
```

The endpoint returns the generated image as `image/png`. The current implementation uses the
`gpt-image-1` model, requests a 1024x1024 image at high quality, and describes the desired
photorealistic appearance in the prompt. GPT Image models return image data as Base64 by default;
the application decodes it before sending the PNG to the client.

### `POST /vision`

Upload a JPEG image as the `file` multipart form field to receive an AI-generated description.
The vision workflow uses the `gpt-4o-mini` model.

```bash
curl --location 'http://localhost:8080/vision' \
  --header 'Content-Type: multipart/form-data' \
  --form 'file=@"/path/to/image.jpg"'
```

In Postman, replace `/path/to/image.jpg` with the image file selected in the form-data editor.
The `postman-cloud:///...` file reference is specific to Postman and is not portable to a
terminal outside that application.

Example response:

```text
AssistantMessage [messageType=ASSISTANT, toolCalls=[], textContent=The image is a vibrant,
intricate illustration that features a multitude of elements arranged in a circular pattern.
The color scheme predominantly includes red and blue against a light background, creating a
striking contrast. In the center, there appears to be a stylized number "250", surrounded by
doodles representing technology, music, transportation, people, and creativity.]
```

## Test the project

```bash
./mvnw test
```

## Course resources

This repository contains source code examples used to support online courses about the Spring
Framework.

### All Spring Framework Guru Courses
### Spring Framework 6
* [Spring Framework 6 - Beginner to Guru](https://www.udemy.com/course/spring-framework-6-beginner-to-guru/?referralCode=2BD0B7B7B6B511D699A9)
* [Hibernate and Spring Data JPA: Beginner to Guru](https://www.udemy.com/course/hibernate-and-spring-data-jpa-beginner-to-guru/?referralCode=251C4C865302C7B1BB8F)
* [API First Engineering with Spring Boot](https://www.udemy.com/course/api-first-engineering-with-spring-boot/?referralCode=C6DAEE7338215A2CF276)
* [Introduction to Kafka with Spring Boot](https://www.udemy.com/course/introduction-to-kafka-with-spring-boot/?referralCode=15118530CA63AD1AF16D)
* [Spring Security: Beginner to Guru](https://www.udemy.com/course/spring-security-core-beginner-to-guru/?referralCode=306F288EB78688C0F3BC)

### Spring Framework 5
* [Spring Framework 5: Beginner to Guru](https://www.udemy.com/testing-spring-boot-beginner-to-guru/?couponCode=GITHUB_REPO) - Get the most modern and comprehensive course available for the Spring Framework! Join over 17,200 over Guru's in an Slack community exclusive to this course! More than 5,700 students have given this 53 hour course a 5 star review!
* [Spring Boot Microservices with Spring Cloud Beginner to Guru](https://www.udemy.com/course/spring-boot-microservices-with-spring-cloud-beginner-to-guru/?referralCode=6142D427AE53031FEF38) - Master Microservice Architectures Using Spring Boot 2 and Cloud Based Deployments with Spring Cloud and Docker
* [Reactive Programming with Spring Framework 5](https://www.udemy.com/reactive-programming-with-spring-framework-5/?couponCode=GITHUB_REPO_SF5B2G) - Keep your skills razor sharp and take a deep dive into Reactive Programming!
* [Testing Spring Boot: Beginner to Guru](https://www.udemy.com/testing-spring-boot-beginner-to-guru/?couponCode=GITHUB_REPO_SF5B2G) - ** Best Selling Course** Become an expert in testing Java and Spring Applications with JUnit 5, Mockito and much more!

### SQL
* [SQL Beginner to Guru: MySQL Edition](https://www.udemy.com/sql-beginner-to-guru-mysql-edition/?couponCode=GITHUB_REPO_SF5B2G) - SQL is a fundamental must have skill, which employers are looking for. Learn to master SQL on MySQL, the worlds most popular database!

### DevOps
* [Apache Maven: Beginner to Guru](https://www.udemy.com/apache-maven-beginner-to-guru/?couponCode=GITHUB_REPO_SF5B2G) - **Best Selling Course** Take the mystery out of Apache Maven. Learn how to use Maven to build your Java and Spring Boot projects!
* [OpenAPI: Beginner to Guru](https://www.udemy.com/course/openapi-beginner-to-guru/?referralCode=0E7F511C749013CA6AAD) - Master OpenAPI (formerly Swagger) to Create Specifications for Your APIs
* [OpenAPI: Specification With Redocly](https://www.udemy.com/course/openapi-specification-redocly-api-documentation/?referralCode=863C443928D61D9A3831)
* [Docker for Java Developers](https://www.udemy.com/docker-for-java-developers/?couponCode=GITHUB_REPO_SF5B2G) - Best Selling Course on Udemy! Learn how you can supercharge your development by leveraging Docker. Collaborate with other students in a Slack community exclusive to the course!
* [Spring Framework DevOps on AWS](https://www.udemy.com/spring-core-devops-on-aws/?couponCode=GITHUB_REPO_SF5B2G) - Learn how to build and deploy Spring applications on Amazon AWS!
* [Ready for Production with Spring Boot Actuator](https://www.udemy.com/ready-for-production-with-spring-boot-actuator/?couponCode=GITHUB_REPO_SF5B2G) - Learn how to leverage Spring Boot Actuator to monitor your applications running in production.

### Web Development with Spring Framework
* [Mastering Thymeleaf with Spring Boot](https://www.udemy.com/mastering-thymeleaf-with-spring/?couponCode=GITHUB_REPO_SF5B2G) - Once you learn Thymeleaf, you'll never want to go back to using JSPs for web development!


## Connect with Spring Framework Guru
* Spring Framework Guru [Blog](https://springframework.guru/)
* Subscribe to Spring Framework Guru on [YouTube](https://www.youtube.com/channel/UCrXb8NaMPQCQkT8yMP_hSkw)
* Like Spring Framework Guru on [Facebook](https://www.facebook.com/springframeworkguru/)
* Follow Spring Framework Guru on [Twitter](https://twitter.com/spring_guru)
* Connect with John Thompson on [LinkedIn](http://www.linkedin.com/in/springguru)