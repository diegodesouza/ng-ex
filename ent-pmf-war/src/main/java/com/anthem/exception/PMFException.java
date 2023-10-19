package com.anthem.exception;

/**
 * General purpose application exception that can nest other exceptions.
 * 
 * @author angela gabert
 */
public class PMFException extends NestedException
{
  public PMFException()
  {
    super();
  }

  public PMFException(String msg)
  {
    super(msg);
  }

  public PMFException(Throwable cause)
  {
    super(cause);
  }

  public PMFException(String msg, Throwable cause)
  {
    super(msg, cause);
  }

}
