/*
2023/08/09 실버4
 */
package Baekjoon.Graph
import java.util.LinkedList
import java.util.Queue

fun main(){
    val size = readln().toInt() //사이즈 입력받기
    val array = Array(size) { Array(size) { 0 } }
    for(i in 0 until size){
        val token = readln().split(" ")
        for(j in 0 until token.size){
            array[i][j] = token[j].toInt()
        }
    }
    println(bfs(array,Pair(0,0)))
}
fun bfs(array: Array<Array<Int>>,start : Pair<Int,Int>) : String{
    val x = arrayOf(1,0)
    val y = arrayOf(0,1)
    val q : Queue<Pair<Int,Int>> = LinkedList()
    val visit = Array(array.size) { Array(array.size) { false } }
    q.add(start) //출발점 넣고.
    while(!q.isEmpty()){ //큐가 빌때까지.
        val now = q.poll()
        val move = array[now.first][now.second]
        if(visit[now.first][now.second])
            continue
        if(move==-1) //도착했음.
            return "HaruHaru"
        visit[now.first][now.second]=true
        for(i in 0 until 2){ //현재 땅에 있는 칸만큼 갈 수 있음.
            val mx = now.first+move*x[i]
            val my = now.second+move*y[i]
            if(mx< 0 || mx >= array.size || my <0 || my>=array.size){
                continue
            }
            if(visit[mx][my])
                continue
            q.add(Pair(mx,my))
        }
    }
    return "Hing"
}