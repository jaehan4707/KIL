# 문제풀이
그래프탐색의 문제인줄 알았지만,그래프 탐색을 사용하는 문제라고 느끼지 못했다.  
문제의 핵심은 궁수의 위치를 결정하는것과 거리를 계산해서 조건에 유효한적을 찾는것이다.  
조건에 유효한적은 가장 가깝고, 가장 왼쪽에 있는 적이다.

```kotlin
fun select(){ //궁수의 위치를 선택하는 작업.
    for(i in 0 until col){
        for( j in i+1 until col){
            for(k in j+1 until  col){
                answer = max(answer,attack(i,j,k))
                loadOrigin()
            }
        }
    }
}
```
나는 다음과 같은 방법으로 3명의 궁수를 위치시켰다.  
다 풀고 다른사람의 코드를 보니 조합을 사용한 사람이 많았는데,   
조합을 코드로 작성할줄 몰라서 저렇게  NC3을 3중 포문을 통해서 구현했다.  
조합을 구현하는 방법도 배워야겠다;;

```kotlin
while(loc>=1) { //종료조건 : 적이 한칸 내리는 대신 궁수를 한칸씩 올려서 끝까지 함.
    val target : MutableList<Triple<Int,Int,Int>> = mutableListOf()
    for (peo in arc) {
        var enemy : MutableList<Triple<Int,Int,Int>> = mutableListOf()
        for (i in 0 until loc) {
            for (j in 0 until col) {
                if (graph[i][j] != 1) //적이 아닐 경우
                    continue
                val enemyDis = dist(Pair(loc,peo),Pair(i,j))
                if(enemyDis>dis) //거리가 크다면
                    continue
                enemy.add(Triple(i,j,enemyDis)) //배열에 적의 위치와 거리를 같이 넣음.
            }
        }
        if(enemy.isEmpty()) continue //적이 비어있다면 궁수는 공격할 적이 없다는 뜻.
        enemy.sortWith(compareBy({ it.third }, { it.second })) //적을 거리순으로 오름차순후, 같은 거리라면 열로 오름차순함.
        target.add(enemy.first()) //가장 가깝고 왼쪽에 있는 적을 공격
    }
    for(ele in target){  //여기서는 중복된 적을 공격할수도 있기에, 아래와 같이 검사함.
        if(graph[ele.first][ele.second]==1) { //적일 경우
            graph[ele.first][ele.second]=0
            result++
        }
    }
    loc-- //궁수를 한칸 올림.
}
```
코드가 길지만 흐름은 단순하다.  
loc는 궁수의 위치다. 나는 적을 한칸씩 내리는 대신 궁수를 한칸씩 올렸다.   
왜냐하면 기존배열을 최소한으로 변경하고 싶었기 때문이다.  
arc는 궁수의 위치가 들어있는 배열이다.   
그래프를 탐색하면서 적이 아니거나, 거리를 구했는데 거리가 궁수의 사정거리보다 길다면 예외처리를 해줬다.
공격할 수 있는 적은 모두 enemy에 넣고, 거리별로 정렬, 거리가 같다면 가장 왼쪽에 있어야 하기에, 행별로 정렬해서
첫번쨰 요소를 공격하는걸로 판단하고 넣어줬다.
궁수는 중북된 타겟을 공격할 수 있기 때문에 타겟수별로 결과를 더하는것이 아닌 유효한 공격만큼 더해야 한다.

