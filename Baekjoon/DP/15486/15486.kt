package Baekjoon.DP.`15486`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

class `15486` {
    val br =BufferedReader(InputStreamReader(System.`in`))
    var N = 0
    var schedule : ArrayList<Pair<Int,Int>> = ArrayList()
    lateinit var dp : Array<Int>
    init{
        input()
        print(solution())
    }
    fun input(){
        N = br.readLine().toInt()
        dp = Array(N+2,{ 0 }) //N+50이 최대일거같음.
        for(i in 0 until N){
            val line = br.readLine().split(" ")
            schedule.add(Pair(line[0].toInt(),line[1].toInt()))
        }
        schedule.add(Pair(0,0))
    }
    fun solution() : Int{
        var now = 0
        for (j in 1 .. N) { //1일부터 N일까지
            now = max(now,dp[j])
            if(j+schedule[j-1].first > N+1) //넘는다면
                continue
            dp[j+schedule[j-1].first] = max(now + schedule[j-1].second, dp[j+schedule[j-1].first])
        }
        return dp.maxOrNull()!!
    }
}
fun main(){
    val solution = `15486`()
}