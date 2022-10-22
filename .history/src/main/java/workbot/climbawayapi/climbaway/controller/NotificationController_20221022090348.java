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

    // List<Notification> getAll();
    Notification findById(long id); 
    List<Notification> findByUserId(long id); // Find all notifications for a scalers
    Notification create(Notification notification);
    Notification update(long id, Notification notification);
    Notification delete(long id);

    //

    @GetMapping
    public List<NotificationResource> getAllNotifications(){
        return notificationMapper.toResource(notificationService.getAll());
    }

    @GetMapping(value = "/{id}")
    public NotificationResource findNotificationById(@PathVariable Long id){
        return notificationMapper.toResource(notificationService.findById(id));
    }

    @GetMapping(value = "/scalers/{id}")
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


    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public List<CategoryResource> getAllCategories(){
        return categoryMapper.toResource(categoryService.getAll());
    }

    @GetMapping(value = "/{id}")
    public CategoryResource findCategoryById(@PathVariable Long id){
        return categoryMapper.toResource(categoryService.findById(id));
    }

    @GetMapping(value = "/climbing-gyms/{id}")
    public List<CategoryResource> findCategoriesByClimbingGymId(@PathVariable Long id){
        return categoryMapper.toResource(categoryService.findByClimbingGymId(id));
    }

    @PostMapping
    public CategoryResource createCategory(@RequestBody CategoryResource resource){
        return categoryMapper.toResource(categoryService.create(categoryMapper.toModel(resource)));
    }

}
