package hello.servlet.domain.member;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음.
 * 실무에서는 ConcurrentHashMap, AtomaticLong 사용 고려
 */
public class MemberRepository {

    private Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final  MemberRepository instancce = new MemberRepository();

    public static MemberRepository getInstance(){
        return instancce;
    }
    private MemberRepository(){
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public List<Member> findAll(){
        // store에 있는 값들을 꺼내 새로운 ArrayList로 만든다
        // store 객체를 보호하기 위함
        return new ArrayList<>(store.values());
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public void clearStore(){
        store.clear();
    }

}
