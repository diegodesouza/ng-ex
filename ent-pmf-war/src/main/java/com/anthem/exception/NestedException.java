package com.anthem.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * A class that allows exceptions to be chained, maintaining a reference
 * to the originating exception and printing the whole stacktrace of
 * the chain. 
 * 
 * @author Sam Shockey
 **/
public class NestedException extends Exception
{
  private Throwable m_throwableCause = null;

  public NestedException()
  {
    super();
  }

  public NestedException(String msg)
  {
    super(msg);
  }

  public NestedException(Throwable cause)
  {
    super(cause.getMessage());
    m_throwableCause = cause;
  }

  public NestedException(String msg, Throwable cause)
  {
    super(msg);
    m_throwableCause = cause;
  }

  public Throwable getCause()
  {
    return m_throwableCause;
  }

  public Throwable initCause(Throwable cause)
  {
    m_throwableCause = cause;
    return this;
  }

  public void printStackTrace()
  {
    super.printStackTrace();

    if (m_throwableCause != null)
    {
      System.err.print("caused by ------>");
      m_throwableCause.printStackTrace();
    }
  }

  public void printStackTrace(PrintStream ps)
  {
    super.printStackTrace(ps);

    if (m_throwableCause != null)
    {
      ps.print("caused by ------>");
      m_throwableCause.printStackTrace(ps);
    }
  }

  public void printStackTrace(PrintWriter pw)
  {
    super.printStackTrace(pw);

    if (m_throwableCause != null)
    {
      pw.print("caused by ------>");
      m_throwableCause.printStackTrace(pw);
    }
  }

}
