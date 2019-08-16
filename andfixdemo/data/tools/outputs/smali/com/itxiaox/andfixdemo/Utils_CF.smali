.class public Lcom/itxiaox/andfixdemo/Utils_CF;
.super Ljava/lang/Object;
.source "Utils.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 5
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static printLog()V
    .locals 2
    .annotation runtime Lcom/alipay/euler/andfix/annotation/MethodReplace;
        clazz = "com.itxiaox.andfixdemo.Utils"
        method = "printLog"
    .end annotation

    .prologue
    .line 8
    const-string/jumbo v0, "\u4fee\u590d"

    .line 9
    .local v0, "error":Ljava/lang/String;
    const-string/jumbo v1, "xiao"

    invoke-static {v1, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 10
    return-void
.end method
