//The SequenceHandler is the piece of code that defines the sequence of events
//that constitute the experiment.
//
//SequenceHandler.Next() will run the next step in the sequence.
//
//We can also switch between the main sequence of events and a subsequence
//using the SequenceHandler.SetLoop command. This takes two inputs:
//The first sets which loop we are in. 0 is the main loop. 1 is the first
//subloop. 2 is the second subloop, and so on.
//
//The second input is a Boolean. If this is set to true we initialise the 
//position so that the sequence will start from the beginning. If it is
//set to false, we will continue from whichever position we were currently in.
//
//So SequenceHandler.SetLoop(1,true) will switch to the first subloop,
//starting from the beginning.
//
//SequenceHandler.SetLoop(0,false) will switch to the main loop,
//continuing from where we left off.

//TODO:
//scroll
//data output
//resume where you left off

package com.sam.webtasks.client;

import java.util.ArrayList;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sam.webtasks.basictools.CheckIdExists;
import com.sam.webtasks.basictools.CheckScreenSize;
import com.sam.webtasks.basictools.ClickPage;
import com.sam.webtasks.basictools.Consent;
import com.sam.webtasks.basictools.Counterbalance;
import com.sam.webtasks.basictools.InfoSheet;
import com.sam.webtasks.basictools.Initialise;
import com.sam.webtasks.basictools.Names;
import com.sam.webtasks.basictools.PHP;
import com.sam.webtasks.basictools.ProgressBar;
import com.sam.webtasks.basictools.Slider;
import com.sam.webtasks.basictools.TimeStamp;
import com.sam.webtasks.iotask1.IOtask1Block;
import com.sam.webtasks.iotask1.IOtask1BlockContext;
import com.sam.webtasks.iotask1.IOtask1InitialiseTrial;
import com.sam.webtasks.iotask1.IOtask1RunTrial;
import com.sam.webtasks.iotask2.IOtask2Block;
import com.sam.webtasks.iotask2.IOtask2BlockContext;
import com.sam.webtasks.iotask2.IOtask2RunTrial;
import com.sam.webtasks.perceptualTask.PerceptBlock;
import com.sam.webtasks.timeBasedOffloading.TimeBlock;
import com.sam.webtasks.iotask2.IOtask2InitialiseTrial;
import com.sam.webtasks.iotask2.IOtask2PreTrial;
import com.sam.webtasks.iotask2.IOtask2ChoiceOverwrite;

public class SequenceHandler {
	public static void Next() {	
		// move forward one step in whichever loop we are now in
		sequencePosition.set(whichLoop, sequencePosition.get(whichLoop) + 1);

		switch (whichLoop) {
		case 0: // MAIN LOOP
			switch (sequencePosition.get(0)) {
			/***********************************************************************
			 * The code here defines the main sequence of events in the experiment *
			 **********************************************************************/
			case 1:
				/*
				if (Counterbalance.getFactorLevel("feedback")==ExtraNames.FEEDBACK) {
					Window.alert("You are in the feedback condition");
				} else {
					Window.alert("You are in the no-feedback condition");
				}
				*/
				
				ClickPage.Run(Instructions.Get(0), "Next");
				break;
			case 2:
				ClickPage.Run(Instructions.Get(1), "Next");
				break;				
			case 3:
				IOtask2Block block1 = new IOtask2Block();
				
				block1.totalCircles = 10;
				block1.nTargets = 0;
				block1.blockNum = 1;
				block1.logDragData = true;
				
				block1.Run();
				break;
			case 4:
				ClickPage.Run(Instructions.Get(2), "Next");
				break;
			case 5:
				IOtask2Block block2 = new IOtask2Block();
				
				block2.totalCircles = 10;
				block2.nTargets = 3;
				block2.offloadCondition = Names.REMINDERS_NOTALLOWED;
				block2.blockNum = 2;
				block2.logDragData = true;
				
				block2.Run();
				break;
			case 6:
				ClickPage.Run(Instructions.Get(3), "Next");
				break;
			case 7:
				IOtask2Block block3 = new IOtask2Block();
				
				block3.nTargets = Params.nTargets;
				block3.totalCircles = 15;
				block3.offloadCondition = Names.REMINDERS_NOTALLOWED;
				block3.blockNum = 3;
				block3.countdownTimer = true;
				block3.logDragData = true;
				
				block3.Run();
				break;
			case 8:
				ClickPage.Run("You will now get 5 more practice trials.", "Next");
				break;
			case 9:
				//forced internal practice, possibly with prediction / feedback
				ExtraNames.blockNum = 4;
				SequenceHandler.SetLoop(4,  true);
				SequenceHandler.Next();
				break;
			case 10:
				//forced internal practice, possibly with prediction / feedback
				ExtraNames.blockNum = 5;
				SequenceHandler.SetLoop(4,  true);
				SequenceHandler.Next();
				break;
			case 11:
				//forced internal practice, possibly with prediction / feedback
				ExtraNames.blockNum = 6;
				SequenceHandler.SetLoop(4,  true);
				SequenceHandler.Next();
				break;
			case 12:
				//forced internal practice, possibly with prediction / feedback
				ExtraNames.blockNum = 7;
				SequenceHandler.SetLoop(4,  true);
				SequenceHandler.Next();
				break;
			case 13:
				//forced internal practice, possibly with prediction / feedback
				ExtraNames.blockNum = 8;
				SequenceHandler.SetLoop(4,  true);
				SequenceHandler.Next();
				break;
			case 14:
				Slider.Run(Instructions.Get(4), "0%", "100%");
				break;
			case 15:
				PHP.logData("slider1", ""+Slider.getSliderValue(), true);
				break;
			case 16:
				ClickPage.Run(Instructions.Get(5), "Next");
				break;
			case 17:
				IOtask2Block block4 = new IOtask2Block();
				
				block4.nTargets = Params.nTargets;
				block4.totalCircles = 15;
				block4.offloadCondition = Names.REMINDERS_MANDATORY_TARGETONLY;
				block4.blockNum = 9;
				block4.logDragData = true;
				
				block4.Run();
				break;	
			case 18:
				ClickPage.Run(Instructions.Get(6), "Next");
				break;
			case 19:
				ClickPage.Run(Instructions.Get(61), "Next");
				break;
			case 20:
				ClickPage.Run(Instructions.Get(62), "Next");
				break;
			case 21:
				IOtask2Block block5 = new IOtask2Block();
				
				block5.nTargets = Params.nTargets;
				block5.totalCircles = 15;
				block5.targetValues.add(1);
				block5.blockNum = 10;
				block5.logDragData = true;
				
				block5.Run();
				break;				
			case 22:
				ClickPage.Run(Instructions.Get(7), "Next");
				break;
			case 23:
				IOtask2Block block6 = new IOtask2Block();
				
				block6.nTargets = Params.nTargets;
				block6.totalCircles = 15;
				block6.standard24blockprac = true;
				block6.blockNum = 11;
				block6.logDragData = true;
				
				block6.Run();
				break;	
			case 24:
				ClickPage.Run(Instructions.Get(8), "Next");
				break;	
			case 25:
				ProgressBar.Initialise();
				ProgressBar.Show();
				ProgressBar.SetProgress(0, 16);
				
				IOtask2Block block7 = new IOtask2Block();
				
				block7.nTargets = Params.nTargets;
				block7.totalCircles = 15;
				block7.standard16block = true;
				block7.updateProgressText = true;
				block7.updateProgress = true;
				block7.countdownTimer = true;
				block7.blockNum = 12;
				block7.logDragData = true;
				
				block7.Run();
				break;
			case 26:
				ProgressBar.Hide();
				Slider.Run(Instructions.Get(11), "0%", "100%");
				
				break;
			case 27:
				PHP.logData("slider2", ""+Slider.getSliderValue(), true);
				
				break;
				
			case 28:
				// log data and check that it saves
				String data = TimeStamp.Now() + ",";
				data = data + SessionInfo.participantID + ",";
				data = data + SessionInfo.gender + ",";
				data = data + SessionInfo.age + ",";
				data = data + Counterbalance.getFactorLevel("feedback") + ",";
				data = data + Counterbalance.getCounterbalancingCell();

				PHP.UpdateStatus("finished");
				PHP.logData("finish", data, true);
				break;
			case 29:
				ClickPage.Run(Instructions.Get(10), "nobutton");
				break;
			}
			break;

		/********************************************/
		/* no need to edit the code below this line */
		/********************************************/

		case 1: // initialisation loop
			switch (sequencePosition.get(1)) {
			case 1:
				// initialise experiment settings
				Initialise.Run();
				break;
			case 2:
				// make sure that a participant ID has been registered.
				// If not, the participant may not have accepted the HIT
				CheckIdExists.Run();
				break;
			case 3:
				// check the status of this participant ID.
				// have they already accessed or completed the experiment? if so,
				// we may want to block them, depending on the setting of
				// SessionInfo.eligibility
				PHP.CheckStatus();
				break;
			case 4:
				// check whether this participant ID has been used to access a previous experiment
				PHP.CheckStatusPrevExp();
				break;
			case 5:
				// clear screen, now that initial checks have been done
				RootPanel.get().clear();

				// make sure the browser window is big enough
				CheckScreenSize.Run(SessionInfo.minScreenSize, SessionInfo.minScreenSize);
				break;
			case 6:
				if (SessionInfo.runInfoConsentPages) { 
					InfoSheet.Run(Instructions.InfoText());
				} else {
					SequenceHandler.Next();
				}
				break;
			case 7:
				if (SessionInfo.runInfoConsentPages) { 
					Consent.Run();
				} else {
					SequenceHandler.Next();
				}
				break;
			case 8:
				//record the participant's counterbalancing condition in the status table				
				if (!SessionInfo.resume) {
					PHP.UpdateStatus("" + Counterbalance.getCounterbalancingCell() + ",1,0,0,0,0");
				} else {
					SequenceHandler.Next();
				}
				break;
			case 9:
				SequenceHandler.SetLoop(0, true); // switch to and initialise the main loop
				SequenceHandler.Next(); // start the loop
				break;
			}
			break;
		case 2: // IOtask1 loop
			switch (sequencePosition.get(2)) {
			/*************************************************************
			 * The code here defines the sequence of events in subloop 2 *
			 * This runs a single trial of IOtask1                       *
			 *************************************************************/
			case 1:
				// first check if the block has ended. If so return control to the main sequence
				// handler
				IOtask1Block block = IOtask1BlockContext.getContext();

				if (block.currentTrial == block.nTrials) {
					SequenceHandler.SetLoop(0, false);
				}

				SequenceHandler.Next();
				break;
			case 2:
				// now initialise trial and present instructions
				IOtask1InitialiseTrial.Run();
				break;
			case 3:
				// now run the trial
				IOtask1RunTrial.Run();
				break;
			case 4:
				// we have reached the end, so we need to restart the loop
				SequenceHandler.SetLoop(2, true);
				SequenceHandler.Next();
				break;
			}
			break;
		case 3: //IOtask2 loop
			switch (sequencePosition.get(3)) {
			/*************************************************************
			 * The code here defines the sequence of events in subloop 3 *
			 * This runs a single trial of IOtask2                       *
			 *************************************************************/
			case 1:
				// first check if the block has ended. If so return control to the main sequence
				// handler
				IOtask2Block block = IOtask2BlockContext.getContext();
				
				if (block.currentTrial == block.nTrials) {
					SequenceHandler.SetLoop(SequenceHandler.previousLoop,  false);
				}
				
				SequenceHandler.Next();
				break;
			case 2:
				IOtask2InitialiseTrial.Run();
				break;
			case 3:
				//present the pre-trial choice if appropriate
				if (IOtask2BlockContext.currentTargetValue() > -1) {
					IOtask2PreTrial.Run();
				} else { //otherwise just skip to the start of the block
					if ((IOtask2BlockContext.getTrialNum() > 0)&&(IOtask2BlockContext.countdownTimer())) {
						//if we're past the first trial and there's a timer, click to begin
						ClickPage.Run("Ready?", "Continue");
					} else {
						SequenceHandler.Next();
					}
				}
				break;
			case 4:
				if (IOtask2BlockContext.getContext().standard24block == true | IOtask2BlockContext.getContext().standard24blockprac == true | IOtask2BlockContext.getContext().standard16block == true) {
					IOtask2ChoiceOverwrite.Run();
				}  else {
					SequenceHandler.Next();
				}
				break;
			case 5:
				if (IOtask2BlockContext.getNTrials() == -1) { //if nTrials has been set to -1, we quit before running
					SequenceHandler.SetLoop(0,  false);
					SequenceHandler.Next();
				} else {
					//otherwise, run the trial
					IOtask2RunTrial.Run();
				}
				break;
			case 6:
				IOtask2PostTrial.Run();
				break;
			case 7:
				//we have reached the end, so we need to restart the loop
				SequenceHandler.SetLoop(3,  true);
				SequenceHandler.Next();
				break;
			}
			break;
		case 4: //practice IOtask2 loop, forced internal condition, potentially with metacognitive feedback
			switch (sequencePosition.get(4)) {
			case 1:
				if (Counterbalance.getFactorLevel("feedback") == ExtraNames.FEEDBACK) {
					Slider.Run("Before the next practice trial, we would like you to tell us "
		                    + "how <b>confident</b> you are that you can accurately perform the task.<br><br>"
		                    + "Please use the scale below to indicate what percentage of "
		                    + "the special circles you will correctly drag to the instructed side. 100% "
		                    + "would mean that you always get every single one correct. 0% would mean that you can never "
		                    + "get any of them correct.", "0%", "100%");
					break;
				}
			case 2:
				if (Counterbalance.getFactorLevel("feedback") == ExtraNames.FEEDBACK) {
					PHP.logData("internalPrediction", ExtraNames.blockNum + "," + Slider.getSliderValue(), true);
				} else {
					SequenceHandler.Next();
				}
				break;
			case 3:
				IOtask2Block trainIntBlock = new IOtask2Block();
				
				trainIntBlock.nTargets = Params.nTargets;
				trainIntBlock.totalCircles = 15;
				trainIntBlock.offloadCondition = Names.REMINDERS_NOTALLOWED;
				trainIntBlock.blockNum = ExtraNames.blockNum;
				trainIntBlock.countdownTimer = true;
				trainIntBlock.logDragData = true;
				
				trainIntBlock.Run();
				break;
			case 4:
				if (Counterbalance.getFactorLevel("feedback") == ExtraNames.FEEDBACK) {
					int percentCorrect = (int) (100 * (IOtask2BlockContext.getnHits() / (float) (Params.nTargets)));

					String feedbackMessage = "";

					if (percentCorrect < Slider.getSliderValue()) {
						feedbackMessage = "You overestimated your memory ability.";
					} else if (percentCorrect > Slider.getSliderValue()) {
						feedbackMessage = "You underestimated your memory ability.";
					} else {
						feedbackMessage = "You accurately estimated your memory ability.";
					}

					ClickPage.Run("You predicted that you would get " + Slider.getSliderValue() + "% correct.<br><br> You actually got " 
							+ percentCorrect + "% correct.<br><br> " + feedbackMessage, "Next");
					break;
				} else {

				  SequenceHandler.Next();
	
				}
				break;	
			case 5:
				//back to main sequence
				SequenceHandler.SetLoop(0,  false);
				SequenceHandler.Next();
				break;
			}
			break;
		}
	}
	
	private static ArrayList<Integer> sequencePosition = new ArrayList<Integer>();
	private static int whichLoop;
	private static int previousLoop=0; //which loop were we running before?

	public static void SetLoop(int loop, Boolean init) {
		if (loop != whichLoop) {
			previousLoop = whichLoop; //make a note of the previous loop that was running
		}
		
		whichLoop = loop;

		while (whichLoop + 1 > sequencePosition.size()) { // is this a new loop?
			// if so, initialise the position in this loop to zero
			sequencePosition.add(0);
		}

		if (init) { // go the beginning of the sequence if init is true
			sequencePosition.set(whichLoop, 0);
		}
	}
	
	// get current loop
	public static int GetLoop() {
		return (whichLoop);
	}

	// set a new position
	public static void SetPosition(int newPosition) {
		sequencePosition.set(whichLoop, newPosition);
	}

	// get current position
	public static int GetPosition() {
		return (sequencePosition.get(whichLoop));
	}
	
	// get current position from particular loop
	public static int GetPosition(int nLoop) {
		return (sequencePosition.get(nLoop));
	}
}
