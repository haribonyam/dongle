package com.dongle.dongle.controller;


import com.dongle.dongle.dto.CommentDto;
import com.dongle.dongle.dto.MemberDto;
import com.dongle.dongle.dto.PostsDto;
import com.dongle.dongle.service.MemberService;
import com.dongle.dongle.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;
    private final MemberService memberService;

    @GetMapping("/write")
    public String contentWrite(Model model){
        String loginNickname = memberService.getUserNickName();
        MemberDto memberDto = memberService.findByNickname(loginNickname);
        model.addAttribute("member",memberDto);
        model.addAttribute("nickname",loginNickname);
        return "postWrite";
    }
    @PostMapping("/save")
    public String contentSave(PostsDto postsDto){
        postsService.savePost(postsDto);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String contentView(@PathVariable Long id, Model model
    ){
        PostsDto postsDto = postsService.getPostById(id);
        model.addAttribute("postView",postsDto);
        model.addAttribute("comments",postsService.findCommentByPostId(id));
        return "postView";
    }

    @GetMapping("/delete/{id}")
    public String contentDelete(@PathVariable Long id){
         postsService.deletePostById(id);
        return"redirect:/";
    }
    @GetMapping("/modify/{id}")
    public String postModify(@PathVariable Long id , Model model){
        PostsDto postsDto = postsService.getPostById(id);
        model.addAttribute("postModify",postsDto);
        return "postModify";
    }
    @PostMapping("/modifyPro/{id}")
    public String postModifyProccess(@PathVariable Long id,PostsDto postsDto){

        postsService.updatePost(id,postsDto);
        return "redirect:/posts/"+id;
    }

    @PostMapping("/comment/save")
    public String commentSave(CommentDto commentDto){

        postsService.saveComment(commentDto);

        return "redirect:/posts/"+commentDto.getPostId();
    }
    @PostMapping("/comment/child")
    public String childSave(CommentDto commentDto){

        postsService.saveChild(commentDto);

        return "redirect:/posts/"+commentDto.getPostId();
    }
}