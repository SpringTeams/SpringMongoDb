package org.spring.mongo.serviceImpl;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.nio.CharBuffer;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service(value = "basicService")
public class BasicService implements Serializable {
    public Boolean isCharacter(String value){
        char[] ch=value.toCharArray();
      Boolean status= CharBuffer.wrap(ch).chars().mapToObj(chs -> (char)chs).filter(new Predicate<Character>() {
            @Override
            public boolean test(Character character) {
                return Character.isAlphabetic(character);
            }
        }).isParallel();
      return status;
    }
}
