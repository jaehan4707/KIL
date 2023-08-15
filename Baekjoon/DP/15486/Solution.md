# 문재 핵심

DP의 핵심은 DP 배열을 설정해서 점화식을 정하는것이다.  
DP[i]는 i 일에서 얻을 수 있는 최대 금액이다.

```kotlin
dp[now+day] = max(dp[now]+ profit,dp[now+day])
```
계속해서 최대값을 갱신해준다.