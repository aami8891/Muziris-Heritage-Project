package com.example.aapplication.Chroma.view;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import com.example.aapplication.Chroma.IndicatorMode;
import com.example.aapplication.Chroma.colormode.Channel;
import com.example.aapplication.R;

public class ChannelView extends RelativeLayout {

    private final Channel channel;
    private final IndicatorMode indicatorMode;

    private Context context;

    private OnProgressChangedListener listener;

    public interface OnProgressChangedListener {
        void onProgressChanged();
    }

    public ChannelView(Channel channel, @ColorInt int color, IndicatorMode indicatorMode, Context context) {
        super(context);
        this.channel = channel;
        this.indicatorMode = indicatorMode;
        this.context = context;

        channel.setProgress(channel.getExtractor().extract(color));
        if(channel.getProgress() < channel.getMin() || channel.getProgress() > channel.getMax()) {
            throw new IllegalArgumentException(
                    "Initial progress " + channel.getProgress()
                            + " for channel: " + channel.getClass().getSimpleName()
                            + " must be between " + channel.getMin() + " and " + channel.getMax());
        }

            View rootView = inflate(context, R.layout.channel_row, this);
            bindViews(rootView);
        }

    private void bindViews(View rootView) {
        TextView label = (TextView) rootView.findViewById(R.id.label);
        label.setText(context.getString(channel.getNameResourceId()));

        final TextView progressView = (TextView) rootView.findViewById(R.id.progress_text);
        setProgress(progressView, channel.getProgress());

        SeekBar seekbar = (SeekBar) rootView.findViewById(R.id.seekbar);
        seekbar.setMax(channel.getMax());
        seekbar.setProgress(channel.getProgress());

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                channel.setProgress(progress);
                setProgress(progressView, progress);
                if(listener != null) {
                    listener.onProgressChanged();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    private void setProgress(TextView view, int progress) {
        view.setText(indicatorMode == IndicatorMode.HEX
                ? Integer.toHexString(progress)
                : String.valueOf(progress));
    }

    public void registerListener(OnProgressChangedListener listener) {
        this.listener = listener;
    }

    public Channel getChannel() {
        return channel;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        listener = null;
    }
}
