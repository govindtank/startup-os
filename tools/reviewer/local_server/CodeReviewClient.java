/*
 * Copyright 2018 The StartupOS Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.startupos.tools.reviewer.localserver;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.inprocess.InProcessChannelBuilder;
import java.util.concurrent.TimeUnit;

public class CodeReviewClient {

  private final ManagedChannel channel;
  private final CodeReviewServiceGrpc.CodeReviewServiceBlockingStub blockingStub;

  CodeReviewClient(String host, int port) {
    this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build());
  }

  CodeReviewClient(String inProcessServerName) {
    this(InProcessChannelBuilder.forName(inProcessServerName).directExecutor().build());
  }

  private CodeReviewClient(ManagedChannel channel) {
    this.channel = channel;
    blockingStub = CodeReviewServiceGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  public String getFile(String name) {
    final FileRequest request = FileRequest.newBuilder().setFilename(name).build();
    try {
      return blockingStub.getFile(request).getContent();
    } catch (StatusRuntimeException e) {
      e.printStackTrace();
      return null;
    }
  }
}
