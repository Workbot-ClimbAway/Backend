package workbot.climbawayapi.climbaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.service.MemberService;
import workbot.climbawayapi.climbaway.mapping.LeagueMapper;
import workbot.climbawayapi.climbaway.mapping.MemberMapper;
import workbot.climbawayapi.security.mapping.ScalersMapper;
import workbot.climbawayapi.climbaway.resource.LeagueResource;
import workbot.climbawayapi.climbaway.resource.MemberResource;
import workbot.climbawayapi.climbaway.resource.SaveMemberResource;
import workbot.climbawayapi.security.resource.ScalersResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/members")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final LeagueMapper leagueMapper;
    private final ScalersMapper scalersMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper, LeagueMapper leagueMapper, ScalersMapper scalersMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
        this.leagueMapper = leagueMapper;
        this.scalersMapper = scalersMapper;
    }

    @GetMapping
    public List<MemberResource> getAllMembers(){
        return memberMapper.toResource(memberService.getAll());
    }

    @GetMapping("/league/{leagueId}")
    public List<ScalersResource> findScalersByLeagueId(@PathVariable long leagueId){
        return scalersMapper.toResource(memberService.findScalersByLeagueId(leagueId));
    }

    @GetMapping("/climbing-gym/{climbingGymId}/scaler/{scalerId}")
    public List<LeagueResource> findLeagueByClimbingGymIdAndScalerId(@PathVariable long climbingGymId, @PathVariable long scalerId){
        return leagueMapper.toResource(memberService.findLeagueByClimbingGymIdAndScalerId(climbingGymId, scalerId));
    }

    @GetMapping("/climbing-gym/{climbingGymId}/league/{leagueId}/scaler/{scalerId}")
    public MemberResource findByClimbingGymIdAndLeagueIdAndScalerId(@PathVariable long climbingGymId, @PathVariable long leagueId, @PathVariable long scalerId){
        return memberMapper.toResource(memberService.findByClimbingGymIdAndLeagueIdAndScalerId(climbingGymId, leagueId, scalerId));
    }

    @PostMapping
    public MemberResource createMember(@RequestBody SaveMemberResource SaveMemberResource, long climbingGymId, long leagueId, long scalerId){
        return memberMapper.toResource(memberService.createMember(memberMapper.toModelSaveResource(SaveMemberResource), climbingGymId, leagueId, scalerId));
    }

    @DeleteMapping
    public MemberResource deleteMember(long climbingGymId, long leagueId, long scalerId){
        return memberMapper.toResource(memberService.deleteMember(climbingGymId, leagueId, scalerId));
    }
}
