//package br.com.alura.forum.dbinitializer;
//
//import br.com.alura.forum.model.Category;
//import br.com.alura.forum.model.Course;
//import br.com.alura.forum.model.User;
//import br.com.alura.forum.model.topic.domain.Topic;
//import br.com.alura.forum.model.topic.domain.TopicStatus;
//import br.com.alura.forum.repository.CategoryRepository;
//import br.com.alura.forum.repository.CourseRepository;
//import br.com.alura.forum.repository.TopicRepository;
//import br.com.alura.forum.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CarregadorDados implements CommandLineRunner {
//
//    private CategoryRepository categoryRepository;
//    private UserRepository userRepository;
//    private CourseRepository courseRepository;
//    private TopicRepository topicRepository;
//
//    public CarregadorDados(CategoryRepository categoryRepository, UserRepository userRepository, CourseRepository courseRepository, TopicRepository topicRepository) {
//        this.categoryRepository = categoryRepository;
//        this.userRepository = userRepository;
//        this.courseRepository = courseRepository;
//        this.topicRepository = topicRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Category programacao = categoryRepository.save(new Category("Programacao"));
//        Category category = categoryRepository.save(new Category("Java", programacao));
//        User user = userRepository.save(new User("Rafael", "rafaeltavares91@gmail.com", "123456"));
//        Course course = courseRepository.save(new Course("Spring boot - fj27", category));
//        Topic topic = new Topic("Problemas da primeira aula", "tive um erro de categoria", user, course);
//        topicRepository.save(topic);
//
//    }
//}
