package com.itplatform.todo.bootstrap;

import com.itplatform.todo.domain.*;
import com.itplatform.todo.service.MemberService;
import com.itplatform.todo.service.TaskService;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private Faker faker;
    private TaskService taskService;
    private MemberService memberService;
    private Priority[] priorities;

    public DataLoader(TaskService taskService, MemberService memberService) {
        this.taskService = taskService;
        this.memberService = memberService;
        faker = new Faker();
        priorities = new Priority[] {Priority.low, Priority.normal, Priority.high};
    }

    @Override
    public void run(String... args) {
        int count = taskService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        Task task = new Task();

        int tasks = 8;
        for (int i = 0; i < tasks; i++) {
            task.setTitle(faker.lorem().sentence());
            task.setDescription(faker.lorem().paragraph());
            task.setDueDate(faker.date().future(90, TimeUnit.DAYS));
            task.setPriority(faker.options().nextElement(priorities));
            task.setStatus(faker.bool().bool());
            task.setComments(new ArrayList<>());

            for (int j = 0; j < faker.number().numberBetween(0, 5); j++) {
                Comment comment = new Comment();
                comment.setText(faker.lorem().sentence());
                comment.setDate(faker.date().past(90, TimeUnit.DAYS));

                task.addComment(comment);
            }

            taskService.save(task);
        }
        loadInitialMember();
    }

    private void loadInitialMember(){
        Member member = new Member();
        member.setMemberId("admin");
        member.setPassword("1234");
        member.setRoleType(MemberRole.ADMIN);

        memberService.save(member);
    }
}
