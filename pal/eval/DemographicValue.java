// DemographicValue.java
//
// (c) 1999-2001 PAL Development Core Team
//
// This package may be distributed under the
// terms of the Lesser GNU General Public License (LGPL)

package pal.eval;

import pal.coalescent.*;
import pal.math.*;
import pal.misc.*;


/**
 * estimates demographic parameters by maximising the coalescent
 * prior for a tree with given branch lengths.
 *
 * @version $Id: DemographicValue.java,v 1.6 2002/04/16 05:37:05 matt Exp $
 *
 * @author Alexei Drummond
 * @authro Korbinian Strimmer
 */
public class DemographicValue implements MultivariateFunction
{
	//
	// Public stuff
	//

	/** Log-Likelihood */
	public double logL;

	/**
	 * define model
	 *
	 * @param m model of demographic
	 */
	public void setDemographicModel(DemographicModel m)
	{
		model = m;
		numParams = model.getNumParameters();


		// likelihood functions contains many local optima
		mvm = new DifferentialEvolution(numParams, 20*numParams);
		//mvm = new ConjugateGradientSearch();
	}

	/**
	 * Returns the demographic model of this likelihood value
	 */
	public DemographicModel getDemographicModel() {
		return model;
	}

	/**
	 * Returns the coalescent tree of this likelihood value.
	 */
	public CoalescentIntervals getCoalescentIntervals() {
		return intervals;
	}

	/**
	 * define coalescent tree.
	 *
	 * @param t tree
	 */
	public void setCoalescentIntervals(CoalescentIntervals ci)
	{
		intervals = ci;
	}

	/**
	 * compute log-likelihood
	 * for current model
	 *
	 * return negative log-likelihood
	 */
	public double compute()
	{
		computeLogLikelihood();

		return -logL;
	}

	/**
	 * optimize log-likelihood
	 * using default optimizer
	 *
	 * return minimum negative log-likelihood
	 */
	public double optimize()
	{
		return optimize(mvm);
	}

	/**
	 * optimize log-likelihood value and compute corresponding SEs
	 * given an optimizer
	 *
	 * @return minimimum negative log-likelihood value
	 */
	public double optimize(MultivariateMinimum givenMvm)
	{
		double[] estimate = new double[numParams];

		for (int i = 0; i < numParams; i++)
		{
			estimate[i] = model.getParameter(i);
		}

		givenMvm.findMinimum(this, estimate,
			BranchLimits.FRACDIGITS, BranchLimits.FRACDIGITS);


		// Corresponding SEs
		double[] estimateSE = new double[numParams];
		estimateSE = NumericalDerivative.diagonalHessian(this, estimate);
		for (int i = 0; i < numParams; i++)
		{
			estimateSE[i] = Math.sqrt(Math.abs(1.0/estimateSE[i]));
			model.setParameterSE(estimateSE[i], i);
		}
		evaluate(estimate);

		model.setLogL(logL);

		return -logL;
	}

	// interface MultivariateFunction

	public double evaluate(double[] params)
	{
		// set tree parameters
		for (int i = 0; i < numParams; i++)
		{
			model.setParameter(params[i], i);
		}

		return compute();
	}

	public int getNumArguments()
	{
		return numParams;
	}

	public double getLowerBound(int n)
	{
		return model.getLowerLimit(n);
	}

	public double getUpperBound(int n)
	{
		return model.getUpperLimit(n);
	}

	//
	// Private stuff
	//

	private int numParams;
	protected CoalescentIntervals intervals;
	protected DemographicModel model;
	private MultivariateMinimum mvm;

	// Calculate likelihood of model given intervals

	protected void computeLogLikelihood() {
		logL = intervals.computeLogLikelihood(model);
	}
	/**
	 * @return null
	 */
	public OrthogonalHints getOrthogonalHints() { return null; }
}

