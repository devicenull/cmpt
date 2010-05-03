// FixedSRDTGlobalClockModel.java
//
// (c) 1999-2004 PAL Development Core Team
//
// This package may be distributed under the
// terms of the Lesser GNU General Public License (LGPL)

package pal.treesearch;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author Matthew Goode
 * @version 1.0
 */
import pal.eval.*;
import pal.misc.*;

public class FixedSRDTGlobalClockModel implements ConstraintModel, ConstraintModel.GroupManager, MolecularClockLikelihoodModel.HeightConverter {
	private final SampleInformation sampleInformation_;
	private final MolecularClockLikelihoodModel.Instance likelihoodModel_;

	private final double rate_;
	private final double maxLeafHeight_;

	public FixedSRDTGlobalClockModel(SampleInformation sampleInformation, MolecularClockLikelihoodModel.Instance likelihoodModel, double rate) {
		this.sampleInformation_ = sampleInformation;
		this.rate_ =rate;
		this.likelihoodModel_ = likelihoodModel;
		this.maxLeafHeight_ = sampleInformation.getMaxHeight();
		System.out.println("Fixed created:"+rate_);
	}
	public String getRateModelSummary() {
		return "Fixed Single Rate Dated Tips, fixed rate = "+getSubstitutionRate();
	}
// =============================================================
// === ConstraintModel Stuff ===================================
	public GroupManager getGlobalClockConstraintGrouping(String[] leafLabelSet) { return this; }
	public String[][] getCladeConstraints(String[] allLabelSet) { return new String[][] { allLabelSet }; }

  public UnconstrainedLikelihoodModel.Leaf createNewFreeLeaf(int[] patternStateMatchup, int numberOfPatterns) { return null; }
	public UnconstrainedLikelihoodModel.External createNewFreeExternal() { return null; }
  public UnconstrainedLikelihoodModel.Internal createNewFreeInternal() { return null; }

	public ConditionalProbabilityStore createAppropriateConditionalProbabilityStore(  boolean isForLeaf ) {
		return likelihoodModel_.createAppropriateConditionalProbabilityStore(isForLeaf);
	}
	public double getSubstitutionRate() { return rate_; }

	public NeoParameterized getGlobalParameterAccess() { return likelihoodModel_.getParameterAccess(); }


// =============================================================
// === GroupManagerStuff Stuff ===================================
	public double getLeafBaseHeight(String leafLabel) {
		return sampleInformation_.getHeight(sampleInformation_.getSampleOrdinal(leafLabel));
	}
	public double getBaseHeight(double originalExpectSubstitutionHeight) {
		double esMaxLeafHeight = maxLeafHeight_*rate_;
		if(originalExpectSubstitutionHeight<esMaxLeafHeight) {
			return originalExpectSubstitutionHeight/rate_;
		}
		return maxLeafHeight_+(originalExpectSubstitutionHeight-esMaxLeafHeight);
	}

	public int getBaseHeightUnits() { return sampleInformation_.getHeightUnits(); }

	public void initialiseParameters(String[] leafNames, double[] leafHeights ) {	}

	public NeoParameterized getAllGroupRelatedParameterAccess() { return null; }
	public NeoParameterized getPrimaryGroupRelatedParameterAccess() { return null; }
	public NeoParameterized getSecondaryGroupRelatedParameterAccess() { return null; }


	public MolecularClockLikelihoodModel.Leaf createNewClockLeaf(PatternInfo pattern, int[] patternStateMatchup) { return likelihoodModel_.createNewLeaf(this,pattern,patternStateMatchup); }
	public MolecularClockLikelihoodModel.External createNewClockExternal() { return likelihoodModel_.createNewExternal(this); }
	public MolecularClockLikelihoodModel.Internal createNewClockInternal() { return likelihoodModel_.createNewInternal(this); }

// =============================================================
// === HeightConverter Stuff ===================================

	public double getExpectedSubstitutionHeight(double baseHeight) {
		if(baseHeight>maxLeafHeight_) {
		  return maxLeafHeight_*rate_+(baseHeight-maxLeafHeight_);
		}
		return baseHeight*rate_;
	}
	public double getExpectedSubstitutionDistance(double lowerBaseHeight, double upperBaseHeight) {
		if(upperBaseHeight<maxLeafHeight_) {
			return( upperBaseHeight-lowerBaseHeight )*rate_;
		} else if(lowerBaseHeight>maxLeafHeight_) {
			return( upperBaseHeight-lowerBaseHeight );
		}
		return (upperBaseHeight-maxLeafHeight_)+(maxLeafHeight_-lowerBaseHeight)*rate_;
	}
} //End of class FixedSRDTGlobalClockModel


