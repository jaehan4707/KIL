# 문제풀이

배열의 최대 크기가 8 x 8 이기 때문에 벽의 개수를 랜덤하게 배열 내에서 3개를 뽑아서 진행했다.
빈칸의 개수 * 빈칸의개수-1 * 빈칸의개수-2 만큼 반복문을 돌렸다.

```kotlin
    for(i in 0 until b.size){
        for(j in i+1 until  b.size){
            for(k in j+1 until b.size){
                val list : MutableList<Pair<Int,Int>> = LinkedList()
                list.add(b[i])
                list.add(b[j])
                list.add(b[k])
                answer = max(answer,dfs(graph,list))
            }
        }
    }
```
빈칸을 3개 뽑아서 탐색을 진행했다.
```kotlin
    while(!tempQueue.isEmpty()){
        val now = tempQueue.poll()
        visit[now.first][now.second]=true
        for(i in 0 until 4){
            val mx = now.first+x[i]
            val my = now.second+y[i]
            if(mx<0 || my < 0 || mx>=row || my>=col)
                continue
            if(tempArray[mx][my]==1 || visit[mx][my] || tempArray[mx][my]==2) //벽이나  바이러스는 안들어감.
                continue
            origin--
            tempArray[mx][my]=2
            tempQueue.add(Pair(mx,my))
        }
    }
```
tempQueue에는 바이러스가 들어있다.
bfs를 진행해서 빈칸의 경우에 queue에 넣고, 바이러스임을 표시했다.
그리고 안전영역의 크기를 한칸 줄였다.