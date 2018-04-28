
using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Threading;
using System.Text;
using System.Collections.Specialized;
using System.Threading.Tasks;
using System.Windows.Forms;
using CsLabs01;

namespace CsLabs02
{

    public class HttpServer
    {

        protected HttpListener listener;

        private string address;

        public HttpServer(String inetAddr, int port)
        {

            listener = new HttpListener();

            if (inetAddr.Contains("http") || inetAddr.Contains(":") ) {
                throw new ArgumentException("Wprowadzono niepoprawne dane!");
            }

            StringBuilder connListenerData = 
                new StringBuilder("http://")
                    .Append(inetAddr)
                    .Append(":")
                    .Append(port.ToString()).Append("/");

            address = connListenerData.ToString();

            Console.WriteLine("Connection listener data: {0}" , connListenerData.ToString());

            listener.Prefixes.Add(connListenerData.ToString());

        }

        public void StartAsyncServer()
        {
            if (listener != null)
            {
                listener.Start();

                while (true)
                {
                    try
                    {
                        HttpListenerContext context = listener.GetContext();
                        ThreadPool.QueueUserWorkItem(o => HandleRequest(context));
                    }
                    catch (Exception e)
                    {
                        Console.WriteLine("Serwer został zatrzymany !");
                    }
                }
            } else
            {
                throw new ArgumentException("Listener nie został zinicjalizowany!");
            }
        }

        public void StopAsyncServer() {

            if (listener != null) {

                try {

                    listener.Stop();
                } catch (ObjectDisposedException e) {

                    throw new ObjectDisposedException("Listener zostal juz zatrzymany !");
                }
            }
        }
        private void HandleRequest(object state)
        {
            try
            {
                HttpListenerContext context = (HttpListenerContext)state;

                context.Response.StatusCode = 200;
                context.Response.SendChunked = true;

                DisplayWebHeaderCollection(context.Request);
                try {
                
                string absoluteUri = context.Request.Url.AbsoluteUri.Replace(address , "");

                if (absoluteUri.Length == 0) {
                    absoluteUri = "index.html";
                }

                string outputData = FileUtils.ReadFileValues(absoluteUri);
                
                if (outputData.Length < 1 ) {
                    context.Response.StatusCode = 404;
                }
        

                byte[] responseBytes = Encoding.UTF8.GetBytes(outputData.ToCharArray());

                context.Response.OutputStream.Write(responseBytes, 0, responseBytes.Length);
                context.Response.OutputStream.Close();

                Console.WriteLine("\n\nWywolano: {0}" , context.Request.Url.AbsoluteUri);
                } catch ( Exception e ) {
                    context.Response.StatusCode = 500;
                }
            } catch (Exception e) {

                throw new Exception("Wystąpił problem w trakcie przetwarzania żądania !",e);

            }
        }
    
        public static void DisplayWebHeaderCollection(HttpListenerRequest request)
        {

            NameValueCollection headers = request.Headers;

            foreach (string key in headers.AllKeys)
            {
                string[] values = headers.GetValues(key);
                if (values.Length > 0)
                {
                    Console.WriteLine("\n\nHeader: {0}", key);
                    Console.Write(" ->   ");
                    foreach (string value in values)
                    {
                        Console.Write("{0}, ", value);
                    }


                }
                else
                    Console.WriteLine("There is no value associated with the header.");
            }
        }
    }
}