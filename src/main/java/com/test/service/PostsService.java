package com.test.service;


import com.test.Dto.GetAllResponseDto;
import com.test.Dto.PostsRequestDto;
import com.test.Dto.PwcheckRequestDto;
import com.test.entity.Posts;
import com.test.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long update(Long id, PostsRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        posts.update(requestDto);
        return posts.getId();
    }

    @Transactional
    public Boolean checkpw(Long id, PwcheckRequestDto pwcheckRequestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (posts.getPassword().equals(pwcheckRequestDto.getPassword())){
            return true;
        }else{
            return false;
        }

    }


    @Transactional
    public Posts search(Long id){
        return (postsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        )
        );
    }


    // 클라 -> 정보 조회를 서버에게 요청 -> 서버는 레포 -> 레포 찾고 서버-> 서버는 모아서 dto라는 그릇으로 담아서 -> 담은걸 클라에게
    @Transactional
    public List<GetAllResponseDto> getall(){
        List<Posts> posts_lst = postsRepository.findAllByOrderByModifiedAtDesc();
//        List<GetAllDto> getAllDto_lst = new ArrayList<>();

//        for(Posts posts : posts_lst) {
//            GetAllDto getAllDto = new GetAllDto(posts.getId(),posts.getTitle(),posts.getPost(), posts.getUsername(), posts.getCreatedAt(),posts.getModifiedAt());
//
//            getAllDto_lst.add(getAllDto);
//        }
//
//        return getAllDto_lst;
// shift + enter로 대안이 나와서 저걸로 할려다가 끝까지 도전해봄.

        List<GetAllResponseDto> getallResponseDtoList = new ArrayList<>();
        for(Posts posts : posts_lst)
        {
            GetAllResponseDto getAllResponseDto = new GetAllResponseDto(posts);
            getallResponseDtoList.add(getAllResponseDto);
        }
        // 리스트 된 posts_lst를 하나씩 풀려면 Posts posts 에 하나씩 대입. 왜 가능하냐 -> Posts 양식을 list 한걸 하나씩 풀려니깐 알고리즘에 배열을 하나씩 출력하는 느낌.
        // 하나씩 나온 posts를 어디다 넣냐. 그대로 getAllResponseDto에 넣는다. 왜냐? 하나씩 받은게 Posts posts 곧  getAllResponseDto가 입력받을려는 파라미터니깐.
        // 이제 저 getallResponseDtoList를 어찌 쓰냐가 문제임.
        // 나중에....
        // 아무리 생각해봐도 1시간가량... Posts post로 보낼 방법이 생각이 너무 안났음 막 쳐봐도
        // 그러다가

        return getallResponseDtoList;
    }


}

//    List<GetAllResponseDto> getallResponseDtoList = new ArrayList<>();


