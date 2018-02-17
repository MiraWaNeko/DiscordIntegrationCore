package chikachi.discord.core.test;

import chikachi.discord.core.Batcher;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BatcherTest {
    @Test
    @SuppressWarnings("unchecked")
    public void testBatcher() {
        final List<String> expected = Lists.newArrayList("foo", "bar", "baz");
        AtomicBoolean consumed = new AtomicBoolean(false);
        Consumer<List<String>> consumer = (List<String> list) -> {
            assertThat(consumed.get(), is(false));
            assertThat(list, is(expected));
            consumed.set(true);
        };
        ScheduledExecutorService mockExecutor = mock(ScheduledExecutorService.class);
        Batcher<String> batcher = new Batcher<>(consumer, 1000, 3, mockExecutor);

        ScheduledFuture firstScheduledFuture = mock(ScheduledFuture.class);
        when(mockExecutor.schedule((Runnable) any(), eq(1000L), eq(TimeUnit.MILLISECONDS))).thenReturn(firstScheduledFuture);

        batcher.queue("foo");

        verify(mockExecutor, times(1)).schedule((Runnable) any(), eq(1000L), eq(TimeUnit.MILLISECONDS));

        ScheduledFuture secondScheduledFuture = mock(ScheduledFuture.class);
        when(mockExecutor.schedule((Runnable) any(), eq(1000L), eq(TimeUnit.MILLISECONDS))).thenReturn(secondScheduledFuture);

        batcher.queue("bar");

        verify(firstScheduledFuture, times(1)).cancel(false);
        verify(mockExecutor, times(2)).schedule((Runnable) any(), eq(1000L), eq(TimeUnit.MILLISECONDS));

        verify(mockExecutor, never()).schedule((Runnable) any(), eq(1L), eq(TimeUnit.MILLISECONDS));
        batcher.queue("baz");

        verify(secondScheduledFuture, times(1)).cancel(false);
        ArgumentCaptor<Runnable> argument = ArgumentCaptor.forClass(Runnable.class);
        verify(mockExecutor, times(1)).schedule(argument.capture(), eq(1L), eq(TimeUnit.MILLISECONDS));

        assertThat(consumed.get(), is(false));
        argument.getValue().run();
        assertThat(consumed.get(), is(true));
    }
}
