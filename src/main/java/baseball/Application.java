package baseball;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
	static List<Integer> computerNum = new ArrayList<>();
	static List<Integer> userNum = new ArrayList<>();
	
    public static void main(String[] args) {
        startBaseballGame();
        
    }
    
    public static void startBaseballGame() {
    	System.out.println("숫자 야구 게임을 시작합니다.");
    	System.out.print("숫자를 입력해주세요 : ");
    	
    	computerNum = getComputerNum();
    	userNum = getUserNum();
    	if (userNum.size() != 3) throw new IllegalArgumentException();
    	
    	getBaseballGameResult();
    	
    }
    
    public static List<Integer> getComputerNum() {
    	while (computerNum.size() < 3) {
    		int randomNumber = Randoms.pickNumberInRange(1, 9);
    		if (!computerNum.contains(randomNumber)) {
    			computerNum.add(randomNumber);
    		}
    	}
    	return computerNum;
    }
    
    public static List<Integer> getUserNum() {
    	String userStr = Console.readLine();
    	
    	return Arrays.stream(userStr.split(""))
    			.mapToInt(Integer::parseInt)
    			.boxed().collect(Collectors.toList());
    }
    
    public static void getBaseballGameResult() {
    	//정답 아니면 getUserNum() & getBaseballGameResult()
    	for (int i=0; i<3; i++) {
    		int targetNum = userNum.get(i);
    	}
    }
    
    public static void isSameWithComputerNum() {
    	
    }
}
