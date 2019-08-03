# HotFixLab 热修复相关实验研究

参考视频：网易云课堂，[Android顶尖技术之一，Tinker热修复](https://study.163.com/course/courseLearn.htm?courseId=1209251804#/learn/live?lessonId=1278919079&courseId=1209251804 "Android顶尖技术之一，Tinker热修复")

相关核心知识点
热修复插桩, ClassLoader， DexClassLoader/PathClassLoader
PathList
   Elemetns[] dex数组， 插桩
   
   
## hotfixlib
hotfixlib 库为手撸hotfix 的代码实现
**核心为 5步：**
>1. 获取自有的dexElements数组
>2. 获取系统的dexElements数组
>3. 合并生成新的dexElement数组
>4. 获取系统的PathList属性
>5. 通过反射将新的dexElements数组赋值给系统的PathList，实现插桩功能
