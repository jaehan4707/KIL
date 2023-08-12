## 문제 핵심
```text
범위가 넓기 때문에 중복으로 탐색을 한다면 아마 시간초과가 발생할 것이다.
따라서 현재 지점에서 얼마나 갈 수 있는지 저장을 한다면 다음 방문때 굳이 탐색을 쭈욱 진행하지 않고 넘어가면 될 것이다.
```

```kotlin
private lateinit var accum : Array<Array<Int>>
accum = Array(size, {Array(size, {1})})
```
얼마나 진행할 수 있는지를 저장하는 배열이다.
1로 초기화한 이유는 당연하게도 그 지점은 갈 수 있기 때문에 1로 초기화를 했다.  
그렇다면 이러한 최대 지점의 칸 수를 어떻게 저장했을까?  
단순하다. dfs의 반환값을 그대로 받으면 된다.  

```kotlin
   fun dfs(row : Int, col : Int) : Int{
        val now = graph[row][col]
        if(accum[row][col]!=1) //이미 한번 거친 좌표라면 탐색을 하지 않고 넣어줌.
            return accum[row][col]
        for(dir in 0 until 4){
            val mx = row + x[dir]
            val my = col + y[dir]
            if(mx !in graph.indices || my !in graph.indices)
                continue
            if(now >= graph[mx][my]) //now가 작아야함. 반.드.시
                continue
            accum[row][col] = max(accum[row][col],dfs(mx,my)+1)
        }
        return accum[row][col]
    }
```
accum[row][col] 값이 1이 아니라면 이미 구한 좌표이기에 탐색을 하지 않고 그대로 반환한다.