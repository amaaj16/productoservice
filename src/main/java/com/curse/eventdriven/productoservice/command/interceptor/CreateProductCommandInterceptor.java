package com.curse.eventdriven.productoservice.command.interceptor;

import com.curse.eventdriven.productoservice.command.CreateProductCommand;
import com.curse.eventdriven.productoservice.core.data.ProductLookupEntity;
import com.curse.eventdriven.productoservice.core.data.ProductLookupRepository;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
@AllArgsConstructor
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private static final Logger LOG = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

    private ProductLookupRepository productLookupRepository;
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index,command) -> {
            if (CreateProductCommand.class.equals(command.getPayloadType())) {

                LOG.info("Intercepted command: "+ command.getPayloadType());

                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
                ProductLookupEntity productLookupEntity = productLookupRepository.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());

                if (productLookupEntity != null) {
                    throw new IllegalStateException(
                            String.format("Product with productId %s or title %s already exist",
                                    createProductCommand.getProductId(),
                                    createProductCommand.getTitle())
                    );


                }
            }
            return command;
        };
    }
}
