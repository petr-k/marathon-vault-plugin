package com.avast.marathon.plugin.vault

import org.asynchttpclient.DefaultAsyncHttpClient

class MarathonClient(marathonUrl: String) {

  def put(appId: String, json: String): String = {
    val client = new DefaultAsyncHttpClient()
    val response = client.preparePut(s"$marathonUrl/v2/apps/$appId").setBody(json).execute().toCompletableFuture.join()
    assert(response.getStatusCode >= 200 && response.getStatusCode < 300, s"Status code ${response.getStatusCode} != 2xx")
    response.getResponseBody
  }

  def delete(appId: String): String = {
    val client = new DefaultAsyncHttpClient()
    val response = client.prepareDelete(s"$marathonUrl/v2/apps/$appId").execute().toCompletableFuture.join()
    assert(response.getStatusCode >= 200 && response.getStatusCode < 300, s"Status code ${response.getStatusCode} != 2xx")
    response.getResponseBody
  }
}
