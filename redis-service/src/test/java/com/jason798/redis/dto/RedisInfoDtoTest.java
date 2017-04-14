package com.jason798.redis.dto;

import com.jason798.redis.dto.info.RedisInfoDto;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Map;


public class RedisInfoDtoTest {


    @Test
    public void testSplit() throws Exception {
        String rawInfo = "# Server\n" +
                "redis_version:2.8.19\n" +
                "redis_git_sha1:00000000\n" +
                "redis_git_dirty:0\n" +
                "redis_build_id:227b3426abec2acc\n" +
                "redis_mode:standalone\n" +
                "os:Linux 2.6.32-504.el6.x86_64 x86_64\n" +
                "arch_bits:64\n" +
                "multiplexing_api:epoll\n" +
                "gcc_version:4.4.7\n" +
                "process_id:3116\n" +
                "run_id:ba8de91491dd3547f5e38f3a5016015a633903fa\n" +
                "tcp_port:6379\n" +
                "uptime_in_seconds:6367990\n" +
                "uptime_in_days:73\n" +
                "hz:10\n" +
                "lru_clock:362811\n" +
                "config_file:\n" +
                "\n" +
                "# Clients\n" +
                "connected_clients:481\n" +
                "client_longest_output_list:0\n" +
                "client_biggest_input_buf:0\n" +
                "blocked_clients:0\n" +
                "\n" +
                "# Memory\n" +
                "used_memory:10840488\n" +
                "used_memory_human:10.34M\n" +
                "used_memory_rss:23117824\n" +
                "used_memory_peak:25368288\n" +
                "used_memory_peak_human:24.19M\n" +
                "used_memory_lua:35840\n" +
                "mem_fragmentation_ratio:2.13\n" +
                "mem_allocator:jemalloc-3.6.0\n" +
                "\n" +
                "# Persistence\n" +
                "loading:0\n" +
                "rdb_changes_since_last_save:0\n" +
                "rdb_bgsave_in_progress:0\n" +
                "rdb_last_save_time:1476440313\n" +
                "rdb_last_bgsave_status:ok\n" +
                "rdb_last_bgsave_time_sec:0\n" +
                "rdb_current_bgsave_time_sec:-1\n" +
                "aof_enabled:0\n" +
                "aof_rewrite_in_progress:0\n" +
                "aof_rewrite_scheduled:0\n" +
                "aof_last_rewrite_time_sec:-1\n" +
                "aof_current_rewrite_time_sec:-1\n" +
                "aof_last_bgrewrite_status:ok\n" +
                "aof_last_write_status:ok\n" +
                "\n" +
                "# Stats\n" +
                "total_connections_received:4808\n" +
                "total_commands_processed:521324\n" +
                "instantaneous_ops_per_sec:0\n" +
                "total_net_input_bytes:55672627\n" +
                "total_net_output_bytes:9943123\n" +
                "instantaneous_input_kbps:0.00\n" +
                "instantaneous_output_kbps:0.00\n" +
                "rejected_connections:0\n" +
                "sync_full:0\n" +
                "sync_partial_ok:0\n" +
                "sync_partial_err:0\n" +
                "expired_keys:921\n" +
                "evicted_keys:0\n" +
                "keyspace_hits:168526\n" +
                "keyspace_misses:2261\n" +
                "pubsub_channels:0\n" +
                "pubsub_patterns:0\n" +
                "latest_fork_usec:1181\n" +
                "\n" +
                "# Replication\n" +
                "role:master\n" +
                "connected_slaves:0\n" +
                "master_repl_offset:0\n" +
                "repl_backlog_active:0\n" +
                "repl_backlog_size:1048576\n" +
                "repl_backlog_first_byte_offset:0\n" +
                "repl_backlog_histlen:0\n" +
                "\n" +
                "# CPU\n" +
                "used_cpu_sys:4283.62\n" +
                "used_cpu_user:2242.34\n" +
                "used_cpu_sys_children:1.14\n" +
                "used_cpu_user_children:0.73\n" +
                "\n" +
                "# Keyspace\n" +
                "db0:keys=6,expires=0,avg_ttl=0\n";
        System.out.println("raw " + rawInfo);
        Map res = RedisInfoDto.split(rawInfo);
        System.out.println("KV:" + res);
        RedisInfoDto dto = RedisInfoDto.build(res);
        System.out.println("DTO:" + dto);
    }

    @Test
    public void testBuild() {
        String info = "# Server\n" +
                "redis_version:2.8.19\n" +
                "redis_git_sha1:00000000\n" +
                "redis_git_dirty:0\n" +
                "redis_build_id:227b3426abec2acc\n" +
                "redis_mode:standalone\n" +
                "os:Linux 2.6.32-504.el6.x86_64 x86_64\n" +
                "arch_bits:64\n" +
                "multiplexing_api:epoll\n" +
                "gcc_version:4.4.7\n" +
                "process_id:3116\n" +
                "run_id:ba8de91491dd3547f5e38f3a5016015a633903fa\n" +
                "tcp_port:6379\n" +
                "uptime_in_seconds:7837500\n" +
                "uptime_in_days:90\n" +
                "hz:10\n" +
                "lru_clock:1832321\n" +
                "config_file:\n" +
                "\n" +
                "# Clients\n" +
                "connected_clients:543\n" +
                "client_longest_output_list:0\n" +
                "client_biggest_input_buf:0\n" +
                "blocked_clients:0\n" +
                "\n" +
                "# Memory\n" +
                "used_memory:12139496\n" +
                "used_memory_human:11.58M\n" +
                "used_memory_rss:23392256\n" +
                "used_memory_peak:25368288\n" +
                "used_memory_peak_human:24.19M\n" +
                "used_memory_lua:61440\n" +
                "mem_fragmentation_ratio:1.93\n" +
                "mem_allocator:jemalloc-3.6.0\n" +
                "\n" +
                "# Persistence\n" +
                "loading:0\n" +
                "rdb_changes_since_last_save:12\n" +
                "rdb_bgsave_in_progress:0\n" +
                "rdb_last_save_time:1478224311\n" +
                "rdb_last_bgsave_status:ok\n" +
                "rdb_last_bgsave_time_sec:0\n" +
                "rdb_current_bgsave_time_sec:-1\n" +
                "aof_enabled:0\n" +
                "aof_rewrite_in_progress:0\n" +
                "aof_rewrite_scheduled:0\n" +
                "aof_last_rewrite_time_sec:-1\n" +
                "aof_current_rewrite_time_sec:-1\n" +
                "aof_last_bgrewrite_status:ok\n" +
                "aof_last_write_status:ok\n" +
                "\n" +
                "# Stats\n" +
                "total_connections_received:8274\n" +
                "total_commands_processed:549575\n" +
                "instantaneous_ops_per_sec:0\n" +
                "total_net_input_bytes:57505339\n" +
                "total_net_output_bytes:11857887\n" +
                "instantaneous_input_kbps:0.00\n" +
                "instantaneous_output_kbps:0.00\n" +
                "rejected_connections:0\n" +
                "sync_full:0\n" +
                "sync_partial_ok:0\n" +
                "sync_partial_err:0\n" +
                "expired_keys:1307\n" +
                "evicted_keys:0\n" +
                "keyspace_hits:175178\n" +
                "keyspace_misses:4956\n" +
                "pubsub_channels:0\n" +
                "pubsub_patterns:0\n" +
                "latest_fork_usec:1551\n" +
                "\n" +
                "# Replication\n" +
                "role:master\n" +
                "connected_slaves:0\n" +
                "master_repl_offset:0\n" +
                "repl_backlog_active:0\n" +
                "repl_backlog_size:1048576\n" +
                "repl_backlog_first_byte_offset:0\n" +
                "repl_backlog_histlen:0\n" +
                "\n" +
                "# CPU\n" +
                "used_cpu_sys:5252.78\n" +
                "used_cpu_user:2877.67\n" +
                "used_cpu_sys_children:1.32\n" +
                "used_cpu_user_children:0.76\n" +
                "\n" +
                "# Keyspace\n" +
                "db0:keys=13,expires=7,avg_ttl=113294228\n";
    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void testGetServerDto() throws Exception {

    }

    @Test
    public void testSetServerDto() throws Exception {

    }

    @Test
    public void testGetClientsDto() throws Exception {

    }

    @Test
    public void testSetClientsDto() throws Exception {

    }

    @Test
    public void testGetMemoryDto() throws Exception {

    }

    @Test
    public void testSetMemoryDto() throws Exception {

    }

    @Test
    public void testGetPersistenceDto() throws Exception {

    }

    @Test
    public void testSetPersistenceDto() throws Exception {

    }

    @Test
    public void testGetStatsDto() throws Exception {

    }

    @Test
    public void testSetStatsDto() throws Exception {

    }

    @Test
    public void testGetReplicationDto() throws Exception {

    }

    @Test
    public void testSetReplicationDto() throws Exception {

    }

    @Test
    public void testGetCpuInfoDto() throws Exception {

    }

    @Test
    public void testSetCpuInfoDto() throws Exception {

    }

    @Test
    public void testGetKeyspaceDtoList() throws Exception {

    }

    @Test
    public void testSetKeyspaceDtoList() throws Exception {

    }


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
