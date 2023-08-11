# 문제 풀이

```text
대륙을 연결하기 위해서는 대륙간의 구별이 필요하다고 생각했다.
대륙간의 구별은 배열을 돌면서 대륙인 경우 거기서부터 출발해서 bfs를 진행해서
더이상 진행하지 못할경우 진행한 전체 구역을 하나의 대륙으로 판정했다.
그리고 바다와 인접한 땅을 이제 대륙의 모서리라고 판단하고, edgeQ에 넣었다.
```

```kotlin
fun island(ary : Array<Array<Int>>,visit : Array<Array<Boolean>>,loc : Pair<Int,Int>,cnt : Int){
    q.add(loc)
    while(!q.isEmpty()){
        val now = q.poll()
        if(visit[now.first][now.second])
            continue
        visit[now.first][now.second]=true
        ary[now.first][now.second]=cnt
        var check = false
        for(i in 0 until 4){
            val mx = now.first +x[i]
            val my = now.second+y[i]
            if(mx!in ary.indices || my!in ary.indices)
                continue
            if(visit[mx][my]) //방문처리.
                continue
            if(ary[mx][my]==0) {//바다 일 경우
                check = true
                continue
            }
            q.add(Pair(mx,my))
        }
        if(check)
            edgeQ.add(now)
    }
}
```

```kotlin
fun connection(ary : Array<Array<Int>>) : Int{
    val tempQ : Queue<Pair<Pair<Int,Int>,Int>> = LinkedList()
    var answer = 100000
    while(!edgeQ.isEmpty()){
        tempQ.add(Pair(edgeQ.poll(),0))
        val marker = ary[tempQ.peek().first.first][tempQ.peek().first.second]
        var visit = Array(size,{ Array(size,{false} )})
        while(!tempQ.isEmpty()){
            val now = tempQ.poll()
            if(visit[now.first.first][now.first.second])
                continue
            visit[now.first.first][now.first.second]=true
            for(i in 0 until 4){
                val mx = now.first.first+x[i]
                val my = now.first.second+y[i]
                if(mx !in ary.indices || my!in ary.indices)
                    continue
                if(visit[mx][my]) //방문처리.
                    continue
                if(ary[mx][my]==marker) //같은 대륙일 경우
                    continue
                if(ary[mx][my]!=0) {
                    answer = min(answer, now.second + 1)
                    continue
                }
                tempQ.add(Pair(Pair(mx,my),now.second+1))
            }
        }
    }
    return answer-1
}
```
edgeQ에서 다른 대륙으로 건너는데 필요한 길이을 구하는 함수이다.
다른대륙이고 바다가 아닐경우 그때 진행한 길이의 최소값을 구한다.

---------
지금 와서 생각해보니 굳이 bfs가 아니어도 될 거 같다.
bfs를 선택하니 방문배열이 필수엿고, 그에 따른 메모리와 시간낭비가 조금 있는거 같다.
그리고 배열의 범위를 검사하는 indices를 처음 알게 되었다. 
생각보다 유용한것같다.