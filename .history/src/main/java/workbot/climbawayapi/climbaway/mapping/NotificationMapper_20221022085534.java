package workbot.climbawayapi.climbaway.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import workbot.climbawayapi.climbaway.domain.model.entity.Notification;
import workbot.climbawayapi.climbaway.resource.NotificationResource;
import workbot.climbawayapi.climbaway.resource.SaveNotificationResource;
import workbot.climbawayapi.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationC implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping

    public NotificationResource toResource(Notification model){
        return mapper.map(model, NotificationResource.class);
    }

    public Notification toModel(NotificationResource resource) {
        return mapper.map(resource, Notification.class);
    }

    public Notification toModelSaveResource(SaveNotificationResource resource) {
        return mapper.map(resource, Notification.class);
    }

    //List Mapping

    public List<NotificationResource> toResource(List<Notification> models){
        return models.stream().map(this::toResource).collect(Collectors.toList());
    }

}
