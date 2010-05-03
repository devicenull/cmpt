// ExponentialGrowth.java
//
// (c) 1999-2001 PAL Development Core Team
//
// This package may be distributed under the
// terms of the Lesser GNU General Public License (LGPL)


package pal.coalescent;

import java.io.*;

import pal.io.*;
import pal.misc.*;

/**
 * This class models an exponentially growing (or shrinking) population
 * (Parameters: N0=present-day population size; r=growth rate).
 * This model is nested with the constant-population size model (r=0).
 *
 * Parts of this class were inspired by C++ code
 * generously provided by Oliver Pybus.
 *
 * @version $Id: ExponentialGrowth.java,v 1.10 2001/07/12 12:17:43 korbinian Exp $
 *
 * @author Alexei Drummond
 * @author Korbinian Strimmer
 */
public class ExponentialGrowth extends ConstantPopulation implements Report, Parameterized, Serializable, Summarizable
{

	//
	// Public stuff
	//

	private static final String[] EG_SUMMARY_TYPES = {"N0","N0SE", "R", "RSE"}; //This is dependent on ConstantPopulation!


	/** growth rate r */
	public double r;


	/** standard error of growth rate r  */
	public double rSE;


	/**
	 * Construct demographic model with default settings
	 */
	public ExponentialGrowth(int units) {

		super(units);

		r = getDefaultValue(1);
	}


	/**
	 * Construct demographic model of an exponentially growing population
	 */
	public ExponentialGrowth(double size, double growth, int units) {

		super(size, units);

		r = growth;
	}

	public String[] getSummaryTypes() {
   	return EG_SUMMARY_TYPES;
	}

	public double getSummaryValue(int summaryType) {  //This is dependent on ConstantPopulation!
		switch(summaryType) {
			case 2 : {
				return r;
			}
			case 3 : {
				return rSE;
			}
			default : {
       	return super.getSummaryValue(summaryType);
			}
		}
	}


	public Object clone() {
		return new ExponentialGrowth(getN0(), getGrowthRate(), getUnits());
	}

	/**
	 * returns growth rate.
	 */
	public double getGrowthRate() {
		return r;
	}

	// Implementation of abstract methods

	public double getDemographic(double t)
	{
		if (r == 0)
		{
			return N0;
		}
		else
		{
			return N0 * Math.exp(-t * r);
		}
	}

	public double getIntensity(double t)
	{
		if (r == 0)
		{
			return t/N0;
		}
		else
		{
			return (Math.exp(t*r)-1.0)/N0/r;
		}
	}

	public double getInverseIntensity(double x)
	{
		if (r == 0)
		{
			return N0*x;
		}
		else
		{
			return Math.log(1.0+N0*x*r)/r;
		}
	}



	// Parameterized interface

	public int getNumParameters()
	{
		return 2;
	}

	public double getParameter(int k)
	{
		if (k == 0) return N0;

		return r;
	}

	public double getUpperLimit(int k)
	{
		double max = 0;
		switch (k)
		{
			case 0: max = 1e50; break;
			case 1: max = 1000; break;
			// we have to to compute lots of exp(rt) !!
			default: break;
		}

		return max;
	}

	public double getLowerLimit(int k)
	{
		double min = 0;
		switch (k)
		{
			case 0: min = 1e-12; break;
			case 1: min = -200; break;
			// we allow also shrinking populations
			default: break;
		}
		return min;
	}

	public double getDefaultValue(int k)
	{

		if (k == 0)
		{
			//arbitrary default values
			if (getUnits() == GENERATIONS) {
				return 1000.0;
			} else {
				return 0.2;
			}
		} else
		{
			return 0; //constant population
		}
	}

	public void setParameter(double value, int k)
	{
		switch (k)
		{
			case 0: N0 = value; break;
			case 1: r = value; break;
			default: break;
		}
	}

	public void setParameterSE(double value, int k)
	{
		switch (k)
		{
			case 0: N0SE = value; break;
			case 1: rSE = value; break;
			default: break;
		}
	}

	public String toString()
	{
		/*
		String s =
			"Exponentially growing population:\n";

		if (getUnits() == GENERATIONS) {
			s += "Effective Population Size = " + N0 + "\n";
			s += "Growth rate (r) = " + r + "\n";
		} else {
			s += "Theta (haploid) = " + (N0 * 2) + "\n";
			s += "Growth rate (rho) = " + r + "\n";
		}
		return s;
		*/

		OutputTarget out = OutputTarget.openString();
		report(out);
		out.close();

		return out.getString();
	}

	public void report(PrintWriter out)
	{
		out.println("Demographic model: exponential growth");
		out.println("Demographic function: N(t) = N0 exp(-r t)");

		out.print("Unit of time: ");
		if (getUnits() == GENERATIONS)
		{
			out.print("generations");
		}
		else
		{
			out.print("expected substitutions");
		}
		out.println();
		out.println();
		out.println("Parameters of demographic function:");
		out.print(" present-day population size N0: ");
		fo.displayDecimal(out, N0, 6);
		if (N0SE != 0.0)
		{
			out.print(" (S.E. ");
			fo.displayDecimal(out, N0SE, 6);
			out.print(")");
		}
		out.println();
		out.print(" growth rate r: ");
		fo.displayDecimal(out, r, 6);
		if (rSE != 0.0)
		{
			out.print(" (S.E. ");
			fo.displayDecimal(out, rSE, 6);
			out.print(")");
		}
		out.println();

		if (getLogL() != 0.0)
		{
			out.println();
			out.print("log L: ");
			fo.displayDecimal(out, getLogL(), 6);
			out.println();
		}
	}
}
