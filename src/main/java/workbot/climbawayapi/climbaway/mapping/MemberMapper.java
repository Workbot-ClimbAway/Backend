package workbot.climbawayapi.climbaway.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import workbot.climbawayapi.climbaway.domain.model.entity.Member;
import workbot.climbawayapi.climbaway.resource.MemberResource;
import workbot.climbawayapi.climbaway.resource.SaveMemberResource;
import workbot.climbawayapi.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class MemberMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping

    public MemberResource toResource(Member model){
        return mapper.map(model, MemberResource.class);
    }

    public Member toModelSaveResource(SaveMemberResource resource) {
        return mapper.map(resource, Member.class);
    }

    //List Mapping

    public List<MemberResource> toResource(List<Member> models){
        return models.stream().map(this::toResource).collect(Collectors.toList());
    }
}
