package com.example.mazika.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.media.session.PlaybackStateCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.bumptech.glide.RequestManager
import com.example.mazika.R
import com.example.mazika.commen.extension.hideStatusBar
import com.example.mazika.data.entities.Song
import com.example.mazika.commen.extension.showContentAboveStatusBar
import com.example.mazika.commen.extension.showContentNormallyUnderStatusBarWithMainColor
import com.example.mazika.commen.extension.showStatusBar

import com.example.mazika.exoplayer.isPlaying
import com.example.mazika.exoplayer.toSong
import com.example.mazika.other.Status.SUCCESS
import com.example.mazika.ui.viewmodels.MainViewModel
import com.example.mazika.ui.viewmodels.SongViewModel
import com.marcinmoskala.arcseekbar.ArcSeekBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_song.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class SongFragment : Fragment(R.layout.fragment_song) {

    @Inject
    lateinit var glide: RequestManager

    private lateinit var mainViewModel: MainViewModel
    private val songViewModel: SongViewModel by viewModels()

    private var curPlayingSong: Song? = null

    private var playbackState: PlaybackStateCompat? = null

    private var shouldUpdateSeekbar = true



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        subscribeToObservers()

        ivPlayPauseDetail.setOnClickListener {
            curPlayingSong?.let {
                mainViewModel.playOrToggleSong(it, true)
            }
        }
        blurImageView.setBlur(25)


        activity?.hideStatusBar()





        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser) {
                    setCurPlayerTimeToTextView(progress.toLong())
                }
                //arcseekbar.progress=progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                shouldUpdateSeekbar = false
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.let {
                    mainViewModel.seekTo(it.progress.toLong())
                    shouldUpdateSeekbar = true
                }
            }
        })

        var repeat=false
        ivAgain.setOnClickListener {
            repeat = repeat==false

            if (repeat){
                mainViewModel.repeat()
                ivAgain.alpha=0.5f
            }else{
                mainViewModel.dontRepeat()
                ivAgain.alpha=1.0f

            }
        }

        ivSkipPrevious.setOnClickListener {
            mainViewModel.skipToPreviousSong()
        }

        ivSkip.setOnClickListener {
            mainViewModel.skipToNextSong()
        }
    }

    private fun updateTitleAndSongImage(song: Song) {
        tvSongName.text = song.title
        tvSongArtist.text = song.subtitle
        glide.load(song.imageUrl).into(ivSongImage)

        //special case
        if (song.subtitle == "Billie Eilish"){
            blurImageView.setImageResource(R.drawable.belliebackground)
            blurImageView.alpha=1.0f
            blurImageView.setBlur(22)
        }else {
            glide.load(song.imageUrl).into(blurImageView)
            blurImageView.alpha = 0.1f
        }


    }

    private fun subscribeToObservers() {
        mainViewModel.mediaItems.observe(viewLifecycleOwner) {
            it?.let { result ->
                when(result.status) {
                    SUCCESS -> {
                        result.data?.let { songs ->
                            if(curPlayingSong == null && songs.isNotEmpty()) {
                                curPlayingSong = songs[0]
                                updateTitleAndSongImage(songs[0])
                            }
                        }
                    }
                    else -> Unit
                }
            }
        }
        mainViewModel.curPlayingSong.observe(viewLifecycleOwner) {
            if(it == null) return@observe
            curPlayingSong = it.toSong()
            updateTitleAndSongImage(curPlayingSong!!)
        }
        mainViewModel.playbackState.observe(viewLifecycleOwner) {
            playbackState = it
            ivPlayPauseDetail.setImageResource(
                if(playbackState?.isPlaying == true) R.drawable.ic_pause else R.drawable.ic_play
            )
            if(playbackState?.isPlaying == true) animationView.playAnimation() else animationView.pauseAnimation()

            seekBar.progress = it?.position?.toInt() ?: 0
        }
        songViewModel.curPlayerPosition.observe(viewLifecycleOwner) {
            if(shouldUpdateSeekbar) {
                seekBar.progress = it.toInt()
                setCurPlayerTimeToTextView(it)
            }
        }
        songViewModel.curSongDuration.observe(viewLifecycleOwner) {
            seekBar.max = it.toInt()
          //  arcseekbar.maxProgress = it.toInt()
            val dateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
            tvSongDuration.text = dateFormat.format(it)
        }
    }

    private fun setCurPlayerTimeToTextView(ms: Long) {
        val dateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
        tvCurTime.text = dateFormat.format(ms)
    }


    override fun onPause() {
        super.onPause()
        activity?.showStatusBar()

    }

    override fun onStop() {
        super.onStop()
        activity?.showStatusBar()
    }

    override fun onDetach() {
        super.onDetach()
        activity?.showStatusBar()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.hideStatusBar()
    }

    override fun onResume() {
        super.onResume()
        activity?.hideStatusBar()

    }
}












