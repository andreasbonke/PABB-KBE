package htwb.ai.pabb.songrelatedservice;

import htwb.ai.pabb.songrelatedservice.models.SongRelatedData;
import htwb.ai.pabb.songrelatedservice.repository.SongsRelatedServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SongRelatedServiceApplication implements CommandLineRunner {

    @Autowired
    private SongsRelatedServiceRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SongRelatedServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        repository.save(new SongRelatedData("1", "Spring was never waiting for us, girl\n" +
                "It ran one step ahead\n" +
                "As we followed in the dance\n" +
                "\n" +
                "Between the parted pages and were pressed\n" +
                "In love's hot, fevered iron\n" +
                "Like a striped pair of pants\n" +
                "\n" +
                "MacArthur's Park is melting in the dark\n" +
                "All the sweet, green icing flowing down\n" +
                "Someone left the cake out in the rain\n" +
                "\n" +
                "I don't think that I can fake it\n" +
                "'Cause it took so long to bake it\n" +
                "And I'll never have that recipe again, oh noooooo\n" +
                "\n" +
                "I recall the yellow cotton dress\n" +
                "Foaming like a wave\n" +
                "On the ground around your knees\n" +
                "Birds like tender babies in your hands\n" +
                "And the old men playing checkers, by the trees\n" +
                "\n" +
                "\n" +
                "MacArthur's Park is melting in the dark\n" +
                "All the sweet, green icing flowing down\n" +
                "Someone left the cake out in the rain\n" +
                "\n" +
                "I don't think that I can take it\n" +
                "'Cause it took so long to bake it\n" +
                "And I'll never have that recipe again, oh noooooo\n" +
                "\n" +
                "There would be another song for me\n" +
                "For I will sing it\n" +
                "There would be another dream for me\n" +
                "Someone will bring it\n" +
                "\n" +
                "I will drink the wine while it is warm\n" +
                "And never let you catch me looking at the sun\n" +
                "And after all the loves of my life\n" +
                "After all the loves of my life, you'll still be the one\n" +
                "\n" +
                "I will take my life into my hands and I will use it\n" +
                "I will win the worship in their eyes and I will lose it\n" +
                "I will have the things that I desire\n" +
                "And my passion flow like rivers through the sky\n" +
                "\n" +
                "And after all the loves of my life\n" +
                "Oh, after all the loves of my life\n" +
                "I'll be thinking of you - and wondering why\n" +
                "\n" +
                "MacArthur's Park is melting in the dark\n" +
                "All the sweet, green icing flowing down\n" +
                "Someone left the cake out in the rain\n" +
                "\n" +
                "I don't think that I can take it\n" +
                "'Cause it took so long to bake it\n" +
                "And I'll never have that recipe again\n" +
                "Oh noooooo, o-oh no-ooooo"));
        repository.save(new SongRelatedData("2", "Gonna find my baby, gonna hold her tight\n" +
                "Gonna grab some afternoon delight\n" +
                "My motto's always been \"When it's right, it's right\"\n" +
                "Why wait until the middle of a cold, dark night\n" +
                "When everything's a little clearer in the light of day\n" +
                "And we know the night is always gonna be here anyway?\n" +
                "\n" +
                "Thinkin' of you's workin' up a appetite\n" +
                "Lookin' forward to a little afternoon delight\n" +
                "Rubbin' sticks and stones together make the sparks ignite\n" +
                "And the thought of lovin' you is gettin' so excitin'\n" +
                "\n" +
                "Skyrockets in flight\n" +
                "Afternoon delight\n" +
                "Afternoon delight\n" +
                "Afternoon delight\n" +
                "\n" +
                "\n" +
                "Started out this mornin' feelin' so polite\n" +
                "I always thought a fish could not be caught who didn't bite\n" +
                "But you got some bait a-waitin' and I think I might\n" +
                "Like nibblin' a little afternoon delight\n" +
                "\n" +
                "Skyrockets in flight\n" +
                "Afternoon delight\n" +
                "Afternoon delight\n" +
                "Afternoon delight\n" +
                "\n" +
                "Be waitin' for me, baby, when I come around\n" +
                "We can make a lot of lovin 'fore the sun go down\n" +
                "\n" +
                "Thinkin' of you's workin' up a appetite\n" +
                "Lookin' forward to a little afternoon delight\n" +
                "Rubbin' sticks and stones together make the sparks ignite\n" +
                "And the thought of rubbin' you is gettin' so excitin'\n" +
                "\n" +
                "Skyrockets in flight\n" +
                "Afternoon delight\n" +
                "Afternoon delight\n" +
                "Afternoon delight\n" +
                "\n" +
                "Afternoon delight\n" +
                "Afternoon delight"));

        for (SongRelatedData songRelatedData : repository.findAll()) {
            System.out.println(songRelatedData);
        }
    }
}
