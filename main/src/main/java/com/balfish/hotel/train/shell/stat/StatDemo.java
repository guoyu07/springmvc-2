package com.balfish.hotel.train.shell.stat;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p>
 * top & vmstat命令详解
 */
public class StatDemo {

 /*   vmstat 2 10
    procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----
    r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
    1  0   6156 241192 504172 3314700    0    0    77    78  286  801  4  1 94  1  0
    0  0   6156 240768 504172 3314728    0    0     0   180 1281 2924  1  1 98  0  0
    0  0   6156 241172 504172 3314696    0    0     0     0 1225 2856  1  1 98  0  0
    0  0   6156 242288 504180 3313400    0    0     0     6 1245 2782  2  0 98  0  0
    0  0   6156 242164 504204 3313304    0    0     0    48 1281 2962  2  1 97  0  0
    0  0   6156 242040 504204 3313304    0    0     0     0 1380 3972  1  0 98  0  0
    0  0   6156 241388 504260 3314208    0    0    34   114 1609 5671  7  2 89  2  0
    0  0   6156 242704 504316 3314452    0    0     0   162 1601 5141  4  1 92  2  0
    0  0   6156 254484 504316 3305928    0    0     0     0 1951 6887  7  2 88  3  0
    */

    /**
     * vmstat命令是最常见的Linux/Unix监控工具, 可以展现给定时间间隔的服务器的状态值,包括服务器的CPU使用率, 内存使用, 虚拟内存交换情况, IO读写情况
     * 一般vmstat工具的使用是通过两个数字参数来完成的, 第一个参数是采样的时间间隔数, 单位是秒, 第二个参数是采样的次数,
     * 上面的参数: 2表示每个两秒采集一次服务器状态, 10表示只采集10次。
     *
     * r 表示运行队列(就是说多少个进程真的分配到CPU), 当这个值超过了CPU数目, 就会出现CPU瓶颈了。这个也和top的负载有关系, 一般负载超过了3就比较高, 超过了5就高, 超过了10就不正常了, 服务器的状态很危险。
     * b 表示阻塞的进程.
     * swpd 虚拟内存已使用的大小, 如果大于0, 表示你的机器物理内存不足了, 如果不是程序内存泄露的原因, 那么你该升级内存了或者把耗内存的任务迁移到其他机器。
     * free 空闲的物理内存的大小
     * buff Linux/Unix系统是用来存储, 目录里面有什么内容, 权限等的缓存, 我本机大概占用300多M
     * cache cache直接用来记忆我们打开的文件,给文件做缓冲.
     * si 每秒从磁盘读入虚拟内存的大小, 如果这个值大于0, 表示物理内存不够用或者内存泄露了, 要查找耗内存进程解决掉。我的机器内存充裕, 一切正常。
     * so 每秒虚拟内存写入磁盘的大小, 如果这个值大于0, 同上。
     * bi Blocks received from a block device (blocks/s).——每秒从块设备接收到的块数，即读块设备
     * bo Blocks sent to a block device (blocks/s).——每秒发送到块设备的块数，即写块设备。
     * in 每秒CPU的中断次数, 包括时间中断
     * cs 每秒上下文切换次数, 例如我们调用系统函数, 就要进行上下文切换, 线程的切换, 也要进程上下文切换, 这个值要越小越好, 太大了, 要考虑调低线程或者进程的数目,例如在apache和nginx这种web服务器中, 我们一般做性能测试时会进行几千并发甚至几万并发的测试, 选择web服务器的进程可以由进程或者线程的峰值一直下调, 压测, 直到cs到一个比较小的值, 这个进程和线程数就是比较合适的值了。系统调用也是, 每次调用系统函数, 我们的代码就会进入内核空间, 导致上下文切换, 这个是很耗资源, 也要尽量避免频繁调用系统函数。上下文切换次数过多表示你的CPU大部分浪费在上下文切换, 导致CPU干正经事的时间少了, CPU没有充分利用, 是不可取的。
     * us 用户CPU时间,
     * sy 系统CPU时间, 如果太高, 表示系统调用时间长, 例如是IO操作频繁。
     * id 空闲CPU时间,
     * wa 等待CPU时间, 一般来说, id + us + sy + wa = 100,一般我认为id是空闲CPU使用率, us是用户CPU使用率, sy是系统CPU使用率。
     * st 来自于一个虚拟机偷取的CPU时间的百分比
     */

     /* top
    top - 10:38:49 up 9 min,  3 users,  load average: 0.61, 0.74, 0.46
    Tasks: 216 total,   3 running, 212 sleeping,   0 stopped,   1 zombie
    %Cpu(s): 50.0 us, 50.0 sy,  0.0 ni,  0.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
    KiB Mem :  8079388 total,  2194832 free,  2743948 used,  3140608 buff/cache
    KiB Swap:  7999484 total,  7999484 free,        0 used.  4464628 avail Mem

    PID USER      PR  NI    VIRT    RES    SHR S  %CPU %MEM     TIME+ COMMAND
    69 root      20   0       0      0      0 S 100.0  0.0   0:00.04 kworker/1:1
    1035 root      20   0  607196 129840 113752 S 200.0  1.6   0:35.38 Xorg
    1 root      20   0  185320   5884   3908 S   0.0  0.1   0:00.98 systemd
    2 root      20   0       0      0      0 S   0.0  0.0   0:00.00 kthreadd
    3 root      20   0       0      0      0 S   0.0  0.0   0:00.00 ksoftirqd/0
    4 root      20   0       0      0      0 S   0.0  0.0   0:00.07 kworker/0:0
    5 root       0 -20       0      0      0 S   0.0  0.0   0:00.00 kworker/0:0H
    6 root      20   0       0      0      0 S   0.0  0.0   0:01.12 kworker/u8:0
    7 root      20   0       0      0      0 S   0.0  0.0   0:00.44 rcu_sched
    8 root      20   0       0      0      0 S   0.0  0.0   0:00.00 rcu_bh
    9 root      20   0       0      0      0 S   0.0  0.0   0:00.18 rcuos/0
    */


    /**
     *   top是linux系统管理的一个主要命令，通过它可以获得比较多的信息
     *
     *  上图中，第一行表示的项目依次为当前时间、系统启动时间、当前系统登录用户数目、平均负载（三个值代表三个时间段的平均负载）
     *   第二行显示的是所有启动的进程．　包括运行的(running)，挂起的(sleeping)，停止的(stopped)，无用的(zombie)．
     *   第三行显示的是目前CPU的使用情况，包括系统占用的比例、用户使用比例、闲置(Idle)比例等(top后按１会出来所有的cpu情况)
     *   第四行显示物理内存的使用情况，包括总的可以使用的内存、已用内存、空闲内存、缓冲区占用的内存。
     *   第五行显示交换分区使用情况，包括总的交换分区、使用的、空闲的和用于高速缓存的大小。

     *  下面的几行则是标识每个进行具体的信息：
     *  PID（Process ID）：进程标示号。
     *  USER：进程所有者的用户名。
     *  PR：进程的优先级别。
     *  NI：进程的优先级别数值。
     *  VIRT：进程占用的虚拟内存值。
     *  RES：进程占用的物理内存值。
     *  SHR：进程使用的共享内存值。
     *  S：进程的状态，其中S表示休眠，R表示正在运行，Z表示僵死状态，N表示该进程优先值是负数。
     *          %CPU：该进程占用的CPU使用率。
     *          %MEM：该进程占用的物理内存和总内存的百分比。
     *  TIME＋：该进程启动后占用的总的CPU时间。
     *  Command：进程启动的启动命令名称，如果这一行显示不下，进程会有一个完整的命令行。
     */

}
