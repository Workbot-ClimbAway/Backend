package workbot.climbawayapi.climbaway.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import workbot.climbawayapi.climbaway.domain.model.entity.Notification;
import workbot.climbawayapi.climbaway.resource.NotificationResource;
import workbot.climbawayapi.climbaway.resource.SaveNotificationResource;
import workbot.climbawayapi.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;


}
