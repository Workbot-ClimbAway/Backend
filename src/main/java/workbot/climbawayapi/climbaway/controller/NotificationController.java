package workbot.climbawayapi.climbaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.service.NotificationService;
import workbot.climbawayapi.climbaway.mapping.NotificationMapper;
import workbot.climbawayapi.climbaway.resource.NotificationResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/notification")

public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    public NotificationController(NotificationService notificationService, NotificationMapper notificationMapper) {
        this.notificationService = notificationService;
        this.notificationMapper = notificationMapper;
    }

    @GetMapping
    public List<NotificationResource> getAllNotifications(){
        return notificationMapper.toResource(notificationService.getAll());
    }

    @GetMapping(value = "/{id}")
    public NotificationResource findNotificationById(@PathVariable Long id){
        return notificationMapper.toResource(notificationService.findById(id));
    }

    @GetMapping(value = "/scalers-controller/{id}")
    public List<NotificationResource> findNotificationsByUserId(@PathVariable Long id){
        return notificationMapper.toResource(notificationService.findByUserId(id));
    }

    @PostMapping
    public NotificationResource createNotification(@RequestBody NotificationResource resource){
        return notificationMapper.toResource(notificationService.create(notificationMapper.toModel(resource)));
    }

    @PutMapping(value = "/{id}")
    public NotificationResource updateNotification(@PathVariable Long id, @RequestBody NotificationResource resource){
        return notificationMapper.toResource(notificationService.update(id, notificationMapper.toModel(resource)));
    }

    @DeleteMapping(value = "/{id}")
    public NotificationResource deleteNotification(@PathVariable Long id){
        return notificationMapper.toResource(notificationService.delete(id));
    }
}
