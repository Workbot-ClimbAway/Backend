package workbot.climbawayapi.climbaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.service.NotificationService;
import workbot.climbawayapi.climbaway.mapping.NotificationsMapper;
import workbot.climbawayapi.climbaway.resource.NotificationResource;
import workbot.climbawayapi.climbaway.resource.SaveNotificationResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/notification")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationsMapper notificationMapper;

    public NotificationController(NotificationService notificationService, NotificationsMapper notificationMapper) {
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

    @GetMapping(value = "/scarlerId/{id}")
    public List<NotificationResource> findNotificationsByUserId(@PathVariable Long id){
        return notificationMapper.toResource(notificationService.findByScalerId(id));
    }

    @PostMapping
    public NotificationResource createNotification(@RequestBody SaveNotificationResource resource, @RequestParam Long id){
        return notificationMapper.toResource(notificationService.create(notificationMapper.toModelSaveResource(resource),id));
    }

    @DeleteMapping(value = "/{id}")
    public NotificationResource deleteNotification(@PathVariable Long id){
        return notificationMapper.toResource(notificationService.delete(id));
    }
}
